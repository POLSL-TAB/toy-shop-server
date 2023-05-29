# Toy Shop Server

some readme here

<pre>
rejestracja użytkownika:
    POST /api/auth/signup
        body:
            {
                "email":"jakis@user",
                "password":"haslo"
                //opt. "name":"imie",
                //opt. "surname":"nazwisko"
            }

zarządzanie użytkownikami (ADMIN):
    GET /api/admin/users/all - pobranie wszystkich
    GET /api/admin/users/get - pobranie jednego
        ex. /api/admin/users/get?email=example@user
    DELETE /api/admin/users/delete - usunięcie
        ex. /api/admin/users/delete?email=example@user
    GET /api/admin/users/role - pobranie ról użytkownika
        ex. /api/admin/users/role?email=example@user
    POST /api/admin/users/role - ustawienie ról
        ex. /api/admin/users/role?email=example@user&roles=USER,ADMIN

koszyk (USER):
    GET /api/cart/items - lista przedmiotów w koszyku
    POST /api/cart/add - dodanie przedmiotu do koszyka
        body:
            {
                    "productId": 1,
                    "quantity": 7
            }
    DELETE /api/cart/delete - usunięcie pozycji z koszyka
        ex. /api/cart/delete?id=1

zamówienia (USER)
    GET /api/order/all - pobranie wszystkich zamówień użytkownika
    PUT /api/order/create - utworzenie zamówienia na podstawie produktów w koszyku
    GET /api/order/complaint/get - pobranie reklamacji do zamówienia
        ex. /api/order/complaint/get?orderId=1
    PUT /api/order/complaint/create - utworzenie reklamacji do zakupu
        body:
            {
                    "orderId": 1,
                    "reason": "jakiś powód reklamacji"
            }

produkty (ALL)
    GET /api/products/all - pobranie wszystkich produktów
    GET /api/products/get - pobranie jednego
        ex. /api/products/get?id=1
    GET /api/products/images/all - pobranie wszystkich obrazków
    GET /api/products/images - pobranie obrazka dla jednego produktu
        ex. /api/products/images?productId=1

obsługa sklepu (STAFF)
    PUT /api/staff/products/add - dodanie produktu
        body:
            {
                "name":"prodTest",
                "description":"Nowy Produkt",
                "price":"12.35",
                "stock":2
            }
    POST /api/staff/product/update - aktualizacja produktu (np. update stanu towaru)
        body:
            {
                "id":1,
                "name":"prod1",
                "description":"opis",
                "price":"12.35",
                "stock":50
            }
    PUT api/staff/products/images/add - dodanie obrazka zakodowanego w base64
        body:
            {
                "productId":1,
                "pictureB64":""
            }
    DELETE api/staff/products/images/delete - usunięcie obrazka
        ex. api/staff/products/images/delete?id=1
    GET /api/staff/complaints/all - pobranie wszystkich reklamacji
    POST /api/staff/complaints/update - aktualizacja (stanu) reklamacji (do rozbudowania ewentualnie)
        body:
            {
                "id":1,
                "status":"  CREATED/SENT_TO_MANUFACTURER/REJECTED_BY_SELLER/
                            REJECTED_BY_MANUFACTURER/WAITING_FOR_DELIVERY/COMPLETED"
            }

</pre>