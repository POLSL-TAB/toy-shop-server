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
    GET /api/admin/users/get - pobranie jednego (nie wiem po co w sumie, ale dodałem)
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
    POST /api/cart/delete - usunięcie pozycji z koszyka
        ex. /api/cart/delete?id=1

zamówienia (USER)
    GET /api/order/all - pobranie wszystkich zamówień użytkownika
    PUT /api/order/create - utworzenie zamówienia na podstawie produktów w koszyku

produkty (ALL)
    GET /api/products/all - pobranie wszystkich produktów
    GET /api/products/get - pobranie jednego
        ex. /api/products/get?id=1

obsługa sklepu (STAFF)
    POST /api/staff/products/add
        body:
            {
                "name":"prodTest",
                "description":"Nowy Produkt",
                "price":"12.35",
                "stock":2
            }
    POST /api/staff/product/update (np. update stanu towaru)
        body:
            {
                "id":1,
                "name":"prod1",
                "description":"opis",
                "price":"12.35",
                "stock":50
            }

</pre>