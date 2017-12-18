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

![alt text](https://github.com/kissLevi/alkfejlBeadando/blob/master/EntityDiagrams/Entity%20Relationship%20Diagram1.png "adatbazis uml")

## Szerepkörök

| Szereplő |                            |
|----------|----------------------------|
| Látogató | Bejelentkezhet, illetve ha nincs még fiókja és szeretne regisztrálhat. |
| Felhasználó | Hirdetést adhat fel, vagy kirakott ajánlatot fogadhat el. Sikeres kézbesítés után értékelést írnak egymásról a felek. |
| Admin    | Egy kitüntetett szerepkörrel rendelkező felhasználó aki látja az összes megrendelést. A felhasználók egyenlegét töltheti fel. |

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
Az alkalmazás szempontjából legfontosabb könyvtárak, app alatt találhatóak:
* classes
  * ad - Hirdetés osztály
  * form checker - Formokhoz
  * payment - payment táblához
  * rating - értékelés osztály
  * user - felhasználó osztály
* components
  * adComponents - hírdetésekhez
    * ad - alap hirdetés komponens
    * ad-extend - hirdetés lejárati idejének meghosszabbítása
    * ad-view - hirdetések megjelenítése
    * newad - új hirdetés hozzáadsa
  * introduction - oldal bemutatására szolgáló komponens
  * loginform - bejelentkezést kezelő
  * mainpage-view - Főoldal, ahova megérkeznek az oldalt felkeresők
  * rate - értékelések komponense
  * rate-view - értékelések megjelenítése
  * registerform - regisztrációs ürlap
  * toolbar - navigáció
  * user - felhasználó komponens
  * user-view - felhasználó megjelenítése
  * write-rating - értékelés írása
* config
  * api.ts - api belépési pontját definiálja
* modules
  * app-router - path végpontok definiálása
  * ui - felhasználói felületet segítő modulok
* services
  * ad - hirdetéseket kiszolgáló service
  * auth - hitelesítés service
  * rating - értékeléseket kiszolgáló
  * user - felhasználókat kiszolgáló

## Kliensoldali szolgáltatások listája
* Regisztáció
* Bejelentkezés
* Hirdetés feladása
* Hirdetések böngészése
* Hirdetés elfogadása - Ha talál egy olyan hirdetést amiről úgy gondolja hogy meg tudja szerezni a kért terméket és le tudja szállítani a megadott helyszínre akkor ezt a hirdetést elvállalhatja az illető és ezzel kiszállítóvá válik.
* Hirdetés feladása - Itt adhat fel egy felhasznaló hirdetést, ha van elegendő egyenlege. A form kitöltésével a hirdetés bekerül az összes hirdetes közé és mások kiszállíthatják a megrendelőnek a kért terméket.
* Saját hirdetések megtekintése - Törölheti illetve meghosszabbíthatja a kiszállításra megengedett időt a felhasználó aki a hirdetés tulajdonosa.
* Elvállalt szállítások - Megtekintheti milyen hirdetéseket fogadott el a jelenlegi bejelentkezett felhasznaló, vagyis milyen kiszállítási kötelezettségei vannak.
* Adatlap menüpont - Itt tekinthető meg az adatlap, lehet módosítani a fekhasználónevet illetve jelszót, valamint az értékeléseket is itt lehet kezelni
* Értékelések - Értékelés írása illetve kapott értékelések megtekintése
* Admin egyenlegfeltöltés - Ha az Admin van bejelentkezve fel tudja tölteni a felhasználók egyenlegét az egyenleg feltöltése menüpont alatt.
* Kijelentkezés

## Funkció folyamat leírás - Hírdetés feladás
A hírdetés feladásához először be kell jelentkezni a bejelentkezési form kitöltésével, majd a Bejelentkezés gomb megnyomásával. Ekkor sikeres bejelentkezés esetén át leszünk irányítva a hirdetések oldalára. A "Hirdetés feladása" gombra kattintva nézetet váltunk, és megjelenik egy form melyben az adatok kitöltésével és a "Megrendelem" gomb megnyomásával valid adatok esetén már fel is raktunk egy hirdetést. Persze ha van rá elég egyenlegünk.

## Tesztelés:

A tesztelést az oldal készítésekor folyamatosan végeztük, minden komponenst és oldalt alaposan átvizsgálva, rossz adatokkal és különböző szerepkörökkel. 

Az automatizált tesztelést Protractor végzi. Ezzel a módszerrel tesztelt funkcionalitások:
* Belépés
* Regisztráció

![alt text](https://github.com/kissLevi/alkfejlBeadando/blob/master/teszt.png "teszt kép")

## Felhasználói dokumentáció:
1) git klónozása
2) acquire projekt betöltése intellij-be vagy netbeansbe.
3) szerver futtatása
  * Netbeans: pom.xml jobb-klikk: Run maven/Goals/spring-boot:run
  * Intellij: Maven-configuration -> Command-line: spring-boot:run
4) angular futtatása
  * visual studio code-ba social-delivery projekt megnyitása
  * node_modules telepítése első klónozáskor: npm install
  * npm start
5) böngésző megnyitása a localhost:4200 oldalon
