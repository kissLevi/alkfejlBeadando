# INFO:
Sok változtatás a codeImp branchen található jelenleg!!


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
* Bejelentkezés, kijelentkezés
* Egyenleg feltöltése
* Hirdetés feladása
* Hirdetés elfogadása
* Kiszallitas teljesitese
* Hirdetések törlése
* Hirdetések módosítása
* Értékelés írása
* Felhasználók módosítása és törlése
* Felhasználók értékeléseinek megtekintése

## Adatbázis diagram

![alt text](https://github.com/kissLevi/alkfejlBeadando/blob/master/Entity%20Relationship%20Diagram2.png "adatbazis uml")

## Szerepkörök

| Szereplő |                            |
|----------|----------------------------|
| Látogató | Böngészheti a kirakott hirdetéseket |
| Felhasználó | Hirdetést adhat fel, vagy kirakott ajánlatot fogadhat el. Pénzt tud befizetni, vagy kivenni. Sikeres kézbesítés után értékelést írnak egymásról a felek. |
| Admin    | Egy kitüntetett szerepkörrel rendelkező felhasználó aki látja az összes megrendelést, és tudja törölni azokat.Ha jogosulatlan kifizetés történt lehetősége van visszaállítani azt. Törölhet felhasználót, egyenlegüket tölti fel. |

## Könyvtárstruktúra (backend)
* `model` Adatbázis entitások és kapcsolataik
  * `post` post metódusokhoz hasznalt tablaszerkezetek
    * `NewAd` Új hírdetés
    * `NewRating` Új értékelés
    * `PostUser` Új user
  * `BaseEntity` Egy alap entitás származtatáshoz
  * `User` A felhasznalók táblája
  * `Ad` A hirdetések táblája
  * `Payment` Fizetéseket tároló entitás
  * `Rating` A hirdetésekhez tartozó értékelés
* `api` Végpontok, HTTP kérések kiszolgálása
  * `LogApiController` Login, logout, regisztrációs végpontok
  * `AdvertisementController` Hirdetésekhez tartozó műveletek
  * `RatingController` Értékeléseket kiszolgáló végpontok
  * `UserController` Felhasznalókhoz kapcsolódó kérések
* `service` Kérés és válasz közötti adat feldolgozása, adatbázissal való kommunikáció
  * `AdService` Hirdetések lekérése, mentése
  * `RatingService` Értékelések
  * `SessionService` Aktuális munkamenetet feldolgozó service
  * `UserService` Felhasznalók adatainak kezelése
* `repository` Adatbázissal való kommunikáció, adatok mentése
  * `AdvertisementRepository` Hirdetések mentése
  * `RatingRepository` Értékelések tárolása
  * `UserRepository`Felhasználók tárolása
  * `PaymentRepository` pénzforgalom nyomonkövetése

## Fejlesztői környezet
1. git verziókezelő telepítése
2. Projekt klónozása lokális gépre: `git clone https://github.com/kissLevi/alkfejlBeadando.git` 
3. IntelliJ vagy NetBeans használata
4. Projekt megnyitása
5. NetBeans esetén pom.xml jobb-klikk: Run maven/Goals/spring-boot:run
6. localhost:8080 böngészőben

## Végpont tervek és leírások
* `GET /` Főoldal
* `POST /api/login` Bejelentkezés
* `GET /api/logout` Kijelentkezés
* `POST /api/register` Regisztrációs oldal
* `GET /api/users` Felhasznalók listázása
* `GET /api/users/:id` Felhasznaló adatlapja
* `DELETE /api/users/:id` Felhasznaló törlése
* `PUT /api/users/:id` Felhasznaló adatainka módosítása
* `GET /api/users/:id/ads` Felhasználó hirdetéseinek listázása
* `GET /api/users/:id/balance` Felhasználó egyenlegének kiirasa
* `POST /api/users/:id/balance` Felhasználó egyenlegének feltöltése
* `GET /api/users/:id/ratings` Felhasználó kapott értékeléseinek listázása
* `GET /api/ads` Hirdetések listázása
* `POST /api/ads` Hirdetés feladása
* `GET /api/ads/:id` Egy hirdetés lapja
* `PUT /api/ads/:id` Egy hirdetés megváltoztatása
* `DELETE /api/ads/:id` Egy hirdetés törlése
* `PUT /api/ads/:id/accept` Hirdetés elfogadása
* `PUT /api/ads/:id/complete` Kiszallítás sikeressé jelölése
* `GET /api/ratings/available` Értékelésre váró felhasználók listája
* `PUT /api/ratings/available/:id` Értékelés írása

## Végpont szekvenciadiagram
![alt text](https://github.com/kissLevi/alkfejlBeadando/blob/master/sequencdiagram.png "vegpont szekv diagram")

## Use-case diagram:

![alt text](https://github.com/kissLevi/alkfejlBeadando/blob/master/alkfejl%20(2).png "use-case uml")

## Fejlesztői környezet:
Visual studio code
Használata:
* git klónozása
* npm install: node-modules telepítése
* npm start: angular szerver indítása
* localhost:4200: böngészőben alkalmazás használata

## Alkalmazott könyvtárstruktúra:
... to be made ...

