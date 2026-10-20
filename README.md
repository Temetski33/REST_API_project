# REST API Project

Spring Boot -pohjainen REST API pienen verkkokaupan tilausten hallintaan. API käyttää olemassa olevaa MariaDB-tietokantaa `db_project_2026`.

## Käynnistys

Vaatimukset: Java 21, Maven 3.9+ ja MariaDB.

```powershell
$env:DB_URL = "jdbc:mariadb://localhost:3306/db_project_2026"
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "salasana"
mvn spring-boot:run
```

Oletusosoite on `http://localhost:8080`.


## API

Kaikki vastaukset ovat JSON-muotoisia.

| Metodi | Polku | Käyttötarkoitus |
|---|---|---|
| GET | `/api/products` | Listaa tuotteet |
| GET | `/api/products?search=term` | Hakee tuotteet nimen perusteella |
| GET | `/api/products?categoryId=1` | Suodattaa kategorian perusteella |
| GET | `/api/products/{id}` | Hakee yhden tuotteen |
| POST | `/api/products` | Luo tuotteen |
| PUT | `/api/products/{id}` | Päivittää tuotteen |
| DELETE | `/api/products/{id}` | Poistaa tuotteen |
| GET/POST | `/api/categories` | Listaa tai luo kategorian |
| GET/PUT/DELETE | `/api/categories/{id}` | Kategorian CRUD-operaatiot |
| GET/POST | `/api/customers` | Listaa tai luo asiakkaan |
| GET/PUT/DELETE | `/api/customers/{id}` | Asiakkaan CRUD-operaatiot |

Esimerkki:

```json
POST /api/products
Content-Type: application/json

{
  "name": "Mekaaninen näppäimistö",
  "description": "USB-C-liitäntä",
  "price": 89.90,
  "stock": 12,
  "category": { "id": 1 }
}
```

## Tietokantaratkaisu

Tietokannan taulut ovat `customers`, `customeraddresses`, `contacts`, `orders`, `orderitems`, `products`, `productcategories`, `suppliers` ja `supplieraddresses`.



## Projektin rakenne

- `entity`-luokat kuvaavat tietokantatauluja.
- `repository`-rajapinnat käyttävät Spring Data JPA:ta.
- `controller`-luokat tarjoavat HTTP-rajapinnan.
- `application.properties` sisältää ympäristömuuttujilla ohjattavan MariaDB-yhteyden.
