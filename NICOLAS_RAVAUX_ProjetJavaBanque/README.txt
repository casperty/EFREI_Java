Pour exécuter proprement le code :
Il faut changer les parametes d'encodage d'eclipse. Preferences > General > Workspace > Text file encoding > ISO-8859-1
Le public static void main se trouve dans BanquePicsou.java

Avancement dans le projet :
Transaction interne faite mais ne fait pas la mise à jour pour un virement differé et ne met pas à jour l'historique... 
(il faudrait mettre un observer dans historique surement)
Transaction externe non faite

Remarque :
Fermer l'application avec la croix ne permet pas d'enregistrer les modifications faites durant la session. C'est un choix arbitraire.