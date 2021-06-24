# Programmierbeleg Paul Fuchs

## Aufgabe war die Programmierung eines Spiels auf der JavaVM 

### Ich habe mich für den Klassiker Schiffe versenken entschieden und die Regeln wie folgt modifiziert

Das Spielfeld beträgt 6x6 Felder in horizontaler und vertikaler Ausrichtung

Jeder Spieler hat 5 Schiffe: <br />
2 ReparierBoote: Länge 1<br />
1 HeliLandeBoot: Länge 2<br />
1 PiratenSchiff: Länge 3<br />
1 SchlachtSchiff: Länge 4<br />
--> insgesamt 11 Treffer+Reparaturen

Jedes Boot hat seine eigene passive Fähigkeit<br />
ReparierBoot: kann in dieser Runde 1 Stelle eines beliebigen Schiffs reparieren, pro Reparatur 1 Runde Cooldown<br />
HeliLandeBoot: ermöglicht es dem Spieler seine SchussAnzahl zurückzusetzen, kann erst angegriffen werden, nachdem das Schlachtschiff zerstört wurde<br />
PiratenSchiff: ermöglicht für diese Runde die Rückgabe eines Feldes, das definitiv von einem Schiff besetzt ist<br />
Schlachtschiff: ermöglicht den Abschuss einer 5 Schuss Salve, Cooldown 2 Runden<br />


Der Helikopter hat eine Schussanzahl von 15 Schuss, diese kann durch das Landen zurückgesetzt werden.<br /><br />
Der Helikopter ist kein Objekt auf dem Spielfeld, sondern nur für die grafische Darstellung von Schüssen.

Die Schiffe werden jede Runde zufällig auf dem Spielfeld verteilt.<br />
Die Schiffe dürfen nicht aneinander stoßen oder sich kreuzen<br />
Die Schiffe dürfen nicht über Eck gebaut werden oder Ausbuchtungen besitzen<br />
Die Schiffe dürfen nicht diagonal aufgebaut sein<br />
Ein Schiff ist versenkt, wenn alle Bestandteile abgeschossen wurden<br />

(Dem Spieler wird das Spielfeld des Gegners der vorherigen Runde angezeigt) - evtl auch nicht
Dem Spieler werden die Trefferpunkte seiner Schiffe und die des Gegners angezeigt

Der Spieler kann für seinen Zug zwischen Reparieren oder Spähen und anschließend Schießen oder Helikopter landen wählen. <br/>
Auswahl Schuss: 1 Schuss oder 5 Schuss Salve; sollte der Einzelschuss treffen, darf der Spieler erneut schießen<br />
Auswahl Spähen: in dieser Runde wird dem Spieler ein Feld angezeigt, das garantiert von einem Schiff besetzt ist, Cooldown 1 Runde<br />
Auswahl Reparieren: wenn eines der beiden Reparierboote noch lebendig ist, kann der Spieler ein beschossenes BootsTeil seiner Wahl reparieren, ggf 2 Reparaturen, sollten beide RepairBoote noch lebendig sein<br />
Auswahl Heli landen: füllt die Schussanzahl des Helikopters wieder auf, in dieser Runde kann der Spieler nicht schießen<br />


Wenn ein Spieler spähen oder reparieren ausgewählt hat, ist der Schuss für den Spieler noch möglich, das Auffüllen der Schussanzahl verbietet den Schuss danach.

Das Spiel ist entschieden, wenn ein Spieler alle Schiffe des Gegners abgeschossen hat.
