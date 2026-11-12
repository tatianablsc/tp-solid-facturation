# TP SOLID – Chapitre 4 – Tatiana Blisac

Dans ce TP, j'ai corrigé une application de facturation qui ne respectait pas 3 principes SOLID : SRP, OCP et LSP.

## Résumé de mes corrections

| Ce qui n'allait pas | Principe | Ce que j'ai fait | Ce que ça apporte |
|---|---|---|---|
| Invoice calculait, affichait et enregistrait | SRP | J'ai fait 3 classes : InvoiceCalculator, InvoicePrinter, InvoiceRepository | Chaque classe a un seul rôle |
| Un if par type de remise | OCP | J'ai créé une interface DiscountStrategy et une classe par remise | Je peux ajouter une remise sans toucher au reste |
| Square changeait la hauteur quand on changeait la largeur | LSP | J'ai créé une interface Shape, Square n'hérite plus de Rectangle | Plus de résultat bizarre |

## Organisation du projet
- `srp` : Invoice, InvoiceLine, InvoiceCalculator, InvoicePrinter, InvoiceRepository
- `ocp` : DiscountStrategy, StandardDiscount, VipDiscount, StudentDiscount, BlackFridayDiscount, DiscountService
- `lsp` : Shape, Rectangle, Square
- `Main` : mes tests

---

## Partie A – SRP

**1.** Invoice fait 3 choses : elle calcule le total, elle affiche la facture et elle l'enregistre.

**2.** Elle a plusieurs raisons de changer : si on change le calcul (par exemple ajouter la TVA), si on change l'affichage (un PDF au lieu de la console) ou si on change la façon d'enregistrer. À chaque fois, on doit modifier la même classe, et on risque de casser autre chose.

**3 et 4.** J'ai fait 3 classes :
- InvoiceCalculator : calcule le total
- InvoicePrinter : affiche la facture
- InvoiceRepository : enregistre la facture

Invoice garde seulement les informations (numéro, client, produits).

## Partie B – Correction SRP

**5 à 8.** J'ai ajouté une classe InvoiceLine pour représenter un produit (nom, prix, quantité). Le total se calcule dans InvoiceCalculator, l'affichage se fait dans InvoicePrinter et l'enregistrement dans InvoiceRepository (dans une liste, ça suffit pour le TP).

**9.** Maintenant chaque classe ne change que pour une seule raison : InvoiceCalculator si le calcul change, InvoicePrinter si l'affichage change, InvoiceRepository si l'enregistrement change.

## Partie C – OCP

**10.** Chaque remise est un `if` dans la même méthode. Pour ajouter une nouvelle remise, je suis obligée de modifier cette méthode alors qu'elle marchait déjà.

**11.** Le signe typique, c'est une liste de `if/else` qui devient plus longue à chaque nouveau type.

**12 et 13.** J'ai créé l'interface DiscountStrategy avec une méthode `apply`, et les classes StandardDiscount, VipDiscount et StudentDiscount.

## Partie D – Correction OCP

**14 et 15.** Chaque remise a sa classe. DiscountService reçoit la remise dans son constructeur et l'applique.

**16 et 17.** J'ai ajouté BlackFridayDiscount (-30 %) en créant juste un nouveau fichier. Je n'ai modifié aucune autre classe, donc j'ai bien ajouté une remise par extension.

## Partie E – LSP

**18 et 19.** Si on utilise un Square comme un Rectangle :
```java
Rectangle r = new Square();
r.setWidth(5);
r.setHeight(4);
// on attend 20, mais on obtient 16
```
`setHeight(4)` a aussi changé la largeur, sans que celui qui utilise le code le sache.

**20.** Square ne se comporte pas comme un Rectangle, donc on ne peut pas l'utiliser à sa place : LSP n'est pas respecté.

**21.** J'ai créé une interface Shape avec `area()`. Rectangle et Square l'implémentent chacune de leur côté.

## Partie F – Correction LSP

**22.** Dans les constructeurs, je refuse les valeurs nulles ou négatives (exception).

**23.** Rectangle calcule largeur × hauteur, Square calcule côté × côté. Les deux respectent Shape.

**24.** Dans le Main, je fais une boucle sur une liste de Shape et je calcule l'aire totale sans savoir si c'est un carré ou un rectangle.

**25.** Il n'y a plus de setters, donc une forme ne peut plus changer après sa création. Rien ne surprend l'appelant.

## Partie G – Diagnostic

| Symptôme | Principe | Priorité | Correction |
|---|---|---|---|
| Classe difficile à décrire en une phrase | SRP | Haute | Découper la classe |
| if/else modifié à chaque nouveau type | OCP | Haute | Une classe par cas (Strategy) |
| Sous-classe qui change une règle de la mère | LSP | Haute | Interface commune |
| Code stable rarement modifié | Aucun | Basse | Ne pas y toucher |
| Partie souvent modifiée | OCP | Très haute | À corriger en premier |

Je corrige d'abord le code qui change souvent, parce que c'est là qu'on fait le plus d'erreurs. Le code qui ne bouge jamais, je le laisse.

## Partie H – Refactoring

J'ai corrigé les problèmes un par un (SRP, puis OCP, puis LSP), avec un commit pour chaque. Je n'ai pas compliqué plus que nécessaire (pas de base de données par exemple). Après chaque correction, j'ai lancé le Main pour vérifier les résultats.

**Avant / après** : pour 100 €, l'ancien code donnait 95 (STANDARD), 80 (VIP) et 90 (STUDENT). Le nouveau code donne les mêmes résultats.

## Tests (Main)
- Facture : 2 claviers à 50 € + 1 souris à 20 € = 120 € ✔
- Remises sur 100 € : 95, 80, 90, 70 ✔
- Aire rectangle 5×4 + carré 4 = 36 ✔

## Questions de synthèse

**26.** SRP : une classe doit avoir une seule raison de changer.

**27.** Une responsabilité peut avoir besoin de plusieurs méthodes. Par exemple, un calculateur peut calculer le total et la TVA : deux méthodes, mais un seul rôle.

**28.** OCP : on ajoute de nouvelles fonctionnalités sans modifier le code qui existe déjà.

**29.** Parce qu'à chaque nouveau cas on modifie la même méthode, qui devient longue et risquée à toucher.

**30.** LSP : une classe fille peut remplacer sa classe mère sans changer le comportement attendu.

**31.** En maths, un carré est un rectangle, donc on a envie de faire un héritage. Mais en code, avec les setters, le carré ne se comporte pas comme un rectangle.

**32.** Si on applique SOLID partout, on crée plein de classes inutiles et le code devient plus compliqué.

**33.** Pour être sûre qu'une correction n'a rien cassé, et savoir tout de suite laquelle pose problème si un test échoue.

## Mes choix
J'ai gardé des solutions simples : injection par constructeur, enregistrement en mémoire, formes qui ne changent plus après création. Le but était de corriger les 3 problèmes sans rendre le code plus compliqué.