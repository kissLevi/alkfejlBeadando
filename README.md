# Termék beszerző oldal

2017 alkalmazások fejlesztése beadandó projekt.

## Kiss Levente és Péter Máté

Az oldalra felhasználók tölthetnek fel hirdetéseket, amelyekben egy termék megvásárlására és annak adott címre és időpontra való kiszállítására keresnek vállalkozókat egy általuk  előre meghatározott áron. Bármely beszerző elfogadhatja az ajánlatot, ezután az adott terméket megvásárolva és leszállítva a vevőnek megkapja a kiírt összeget.

## Tartalomjegyzék
* Funkcionális követelmények
* Adatbázis diagram
* Szerepkörök

## Funkcionális követelmények

Az oldal célja hogy a hírdetés feladója utánajárás nélkül, biztonságosan hozzájusson a neki kellő termékhez, míg a beszerzőknek szabadságot ad a profit maximalizálása érdekében.
* Hirdetések böngészése
* Regisztráció
* Bejelentkezés
* Egyenleg feltöltése
* Hirdetés feladása
* Hirdetés elfogadása
* Értékelés írása

## Adatbázis diagram

![alt text](https://github.com/kissLevi/alkfejlBeadando/blob/master/Entity%20Relationship%20Diagram1.png "adatbazis uml")

## Szerepkörök

| Szereplő |                            |
|----------|----------------------------|
| Látogató | Böngészheti a kirakott hirdetéseket |
| Felhasználó | Hirdetést adhat fel, vagy kirakott ajánlatot fogadhat el. Pénzt tud befizetni, vagy kivenni. |
| Admin    | Egy kitüntetett szerepkörrel rendelkező felhasználó aki látja az összes megrendelést, és tudja törölni azokat.Ha jogosulatlan kifizetés történt lehetősége van visszaállítani azt. |
