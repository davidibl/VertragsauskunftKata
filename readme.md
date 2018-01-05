# Function Microservice Kata

Die Beispieldomäne dieser Übung ist ein einfacher Microservice, der 4 Resourcen zur Verüfung stellt.
Die Resourcen sollen einen definierten Output liefern.


# functional programming

Seit Version 8 unterstützt Java anonyme Delgaten in Form von Lambda Expressions.
In dieser Übung soll die Verwendung der selbigen ausprobiert werden.


# Regeln

Damit alles nicht zu einfach ist, gelten folgende Regeln:

- Die Domänenmodelle dürfen nicht verändert werden.
- Die Repositories dürfen nicht verändert werden.
- Es sind keine Kontrollstrukturen erlaubt. Verboten sind entsprechend if, else, switch, tenerärer Ausdruck, while, for etc.

# Voraussetzungen

Die Vertragresource definiert die Schnittstellen. In der Klasse Apptests sind 10 Tests implementiert und
mit jeweils einem Kommentar versehen, die die Spezifikation darstellen.
Die Repositories Kundenrepository und Vertragsrepository liefern die Daten aus den json Files unter
*src/test/resource* bereits als Java Klassen. Die Repositories müssen abfgefragt werden um die Daten
aus den Files zu bekommen.
Die ganze Übung kann unter Verzicht auf jegliche Kontrollstruktur unter Verwendung von Lambda Expressions,
Streams und Optional API gelöst werden.


# Lösung

Die Lösung findet sich im zweiten Branch dieses Repositories.


# Technik

Verwendet wird Maven und Spring Boot.