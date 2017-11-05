# Termék beszerző oldal

2017 alkalmazások fejlesztése beadandó projekt.

## Kiss Levente és Péter Máté

Az oldalra felhasználók tölthetnek fel hirdetéseket, amelyekben egy termék megvásárlására és annak adott címre és időpontra való kiszállítására keresnek vállalkozókat egy általuk  előre meghatározott áron. Bármely beszerző elfogadhatja az ajánlatot, ezután az adott terméket megvásárolva és leszállítva a vevőnek megkapja a kiírt összeget.

## Tartalomjegyzék
* Funkcionális követelmények
* Adatbázis diagram
* Szerepkörök
* Könyvtárstruktúra (backend)
* Fejlesztői környezet
* Végpont tervek és leírások
* Végpont szekvenciadiagram

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

## Könyvtárstruktúra (backend)
* `model` Adatbázis entitások és kapcsolataik
  * `BaseEntity` Egy alap entitás származtatáshoz
  * `User` A felhasznalók táblája
  * `Ad` A hirdetések táblája
  * `Payment` Fizetéseket tároló entitás
  * `Rating` A hirdetésekhez tartozó értékelés
* `view` Megjelenítést biztosítő fájlok
* `controller` Végpontok, HTTP kérések kiszolgálása
* `service` Felhasználók validálása
* `repository` Adatbázissal való kommunikáció

## Fejlesztői környezet
1. git verziókezelő telepítése
2. Projekt klónozása lokális gépre: `git clone https://github.com/kissLevi/alkfejlBeadando.git` 
3. IntelliJ vagy NetBeans használata
4. Projekt megnyitása
5. NetBeans esetén pom.xml jobb-klikk: Run maven/Goals/spring-boot:run
6. localhost:8080 böngészőben

## Végpont tervek és leírások
* `GET /` Főoldal
* `GET /login` Bejelentkező oldal
* `POST /login` Bejelentkezés
* `GET /logout` Kijelentkezés
* `GET /register` Regisztrációs oldal
* `POST /register` Regisztrációs adatok elküldése
* `GET /user` Felhasznalók listázása
* `GET /user/:id` Felhasznaló adatlapja
* `GET /ads` Hirdetések listázása
* `GET /ads/add` Hirdetés feladási oldal
* `POST /ads/add` Hirdetés feladása
* `GET /ads/:id` Egy hirdetés lapja
* `GET /ads/:id/delete` Hirdetés törlése
* `GET /ads/:id/deliver` Hirdetés elfogadása
* `GET /balance` Egyenlegfeltöltő
* `POST /balance` Egyenleg feltöltése
* `GET /ads/:id/review` Értékelés író felület
* `POST /ads/:id/review` Értékelés elküldése

## Végpont szekvenciadiagram
![alt text](https://github.com/kissLevi/alkfejlBeadando/blob/master/sequencdiagram.png "vegpont szekv diagram")
