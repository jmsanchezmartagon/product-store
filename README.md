# 🛒 Price Service – Spring Boot Hexagonal Architecture

## Overview

This project is a **Price Service** for an e-commerce platform, implemented with **Spring Boot** and following *
*Hexagonal Architecture (Ports & Adapters)**.

The service allows querying the applicable price for a product at a given date and returns the final price based on
tariff priority.

The service uses an **H2 in-memory database** initialized with example data from the `PRICES` table.

---

## Database Example: `PRODUCT_PRICES`

| BRAND_ID | START_AT            | END_AT              | PRODUCT_RATE | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|---------------------|---------------------|--------------|------------|----------|-------|------|
| 1        | 2020-06-14 00:00:00 | 2020-12-31 23:59:59 | 1            | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14 15:00:00 | 2020-06-14 18:30:00 | 2            | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15 00:00:00 | 2020-06-15 11:00:00 | 3            | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15 16:00:00 | 2020-12-31 23:59:59 | 4            | 35455      | 1        | 38.95 | EUR  |

### Table Fields

- **BRAND_ID**: Chain identifier
- **START_AT / END_AT**: Validity range of the price
- **PRODUCT_RATE**: Price tariff identifier
- **PRODUCT_ID**: Product identifier
- **PRIORITY**: Disambiguate overlapping tariffs; higher value wins
- **PRICE**: Final sale price
- **CURR**: Currency (ISO code)

---

## Running the Application

### Clone the repository:

```
git clone <repository-url>
cd <repository-folder>
```

### Build the project:

```
mvn clean install
```

### Run the application:

```
mvn spring-boot:run
```

### Access the API:

```
curl -X GET "http://localhost:8080/brand/97881746-0fc5-4c5d-b0e1-238a54237972/product/b25ca18c-a55d-479d-986b-4a95f7b95a19/price?datetime=2025-08-17T15:30:00" -H "accept: application/json"
```