# Programmierbeleg Paul Fuchs

## Aufgabe war die Programmierung eines Spiels auf der JavaVM 

### Ich habe mich für den Klassiker Schiffe versenken entschieden und die Regeln wie folgt modifiziert

Das Spielfeld beträgt 8x8 Felder in horizontaler und vertikaler Ausrichtung

Jeder Spieler hat 5 Schiffe: <br />
2 ReparierBoote: Länge 1<br />
1 HeliLandeBoot: Länge 2<br />
1 PiratenSchiff: Länge 3<br />
1 SchlachtSchiff: Länge 4<br />
--> insgesamt 11 Treffer+Reparaturen

Jedes Boot hat seine eigene passive Fähigkeit<br />
ReparierBoot: kann in dieser Runde 1 Stelle eines beliebigen Schiffs reparieren<br />
HeliLandeBoot: ermöglicht es dem Spieler seine SchussAnzahl zurückzusetzen, es kann erst angegriffen werden, nachdem das Schlachtschiff zerstört wurde<br />
PiratenSchiff: ermöglicht für diese Runde die Rückgabe eines Feldes, das definitiv von einem Schiff besetzt ist<br />
Schlachtschiff: ermöglicht den Abschuss einer 5 Schuss Salve<br />


Der Helikopter hat eine Schussanzahl von 15 Schuss, diese kann durch das Landen zurückgesetzt werden.<br /><br />
Der Helikopter ist kein Objekt auf dem Spielfeld und nur für die Überprüfung der verbliebenen Anzahl an Spielerschüssen notwendig.

Die Schiffe werden jede Runde zufällig auf dem Spielfeld verteilt.<br />
Die Schiffe dürfen keine Ausbuchtungen haben.</br>
Die Schiffe dürfen nicht diagonal aufgebaut sein<br />
Ein Schiff ist versenkt, wenn alle Bestandteile abgeschossen wurden<br />

Dem aktiven Spieler wird sein eigenes Spielfeld, die abgeschossenen Schiffe des Gegners, und die eigenen Schiffe mit abgeschossenen Teilen (insofern vorhanden) angezeigt.

Der Spieler kann für seinen Zug zwischen Reparieren oder Spähen und anschließend Schießen oder Helikopter landen wählen. <br/>
Auswahl Schuss: 1 Schuss oder 5 Schuss Salve; sollte der Einzelschuss treffen, darf der Spieler erneut schießen, bei Wahl 5-Schuss-Salve, ist nach 5 Schüssen der Gegner am Zug.<br />
Auswahl Spähen: in dieser Runde wird dem Spieler ein Feld angezeigt, das garantiert von einem Schiff besetzt ist<br />
Auswahl Reparieren: wenn eines der beiden Reparierboote noch lebendig ist, kann der Spieler ein beschossenes BootsTeil seiner Wahl reparieren, ggf 2 Reparaturen, sollten beide RepairBoote noch lebendig sein<br />
Auswahl Heli landen: füllt die Schussanzahl des Helikopters wieder auf, in dieser Runde kann der Spieler nicht schießen<br />


Wenn ein Spieler spähen oder reparieren ausgewählt hat, ist der Schuss für den Spieler noch möglich, das Auffüllen der Schussanzahl verbietet den Schuss danach.
Jede Fähigkeit der nicht abgeschossenen Boote ist einmal pro Runde einsetzbar.
Das Spiel ist entschieden, wenn ein Spieler alle Schiffe des Gegners abgeschossen hat.
