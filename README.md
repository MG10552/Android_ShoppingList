# Android_ShoppingList
Fairly simple shopping list for Android systems made with Java.

Zadanie polega na stworzeniu aplikacji mającej na celu zarządzanie oraz zapisywanie i odczytywanie listy zakupów przy wykorzystaniu androidowych metod przechowywania danych (SharedPreferences, SQLite, opcjonalnie Content Provider).



Wskazowki:

- Wykorzystanie kilku Activity (mogą być różne typy jak np. ListActivity, PreferenceActivity) oraz Intent do nawigacji pomiędzy widokami w aplikacji. Poniżej znajduje się wylistowany minimalny zestaw Activities:

- MainActivity: główne okno do nawigacji, znajdują się tu guziki do nawigacji do kolejnych komponentów graficznych. 

- ProductListActivity: zestawienie reprezentujące listę zakupów. Każdy element w liście powinien posiadać następujące informacje: nazwa produktu, cena, ilość, oznaczenie czy zostało już zakupione. Dodatkowo powinny znaleźć się elementy GUI (w dowolnym miejscu) odpowiedzialne za dodawanie nowych produktów do listy, modyfikację oraz usuwanie istniejących.

- OptionsActivity: ekran reprezentujący opcje związane z aplikacją. Co najmniej 2 (np. rozmiar i kolor dowolnych elementów w apce).

Wymagania:

1) Należy zapisać wartości opcji za pomocą SharedPreferences. Przy ponownym odpaleniu apki odczytujemy poprzednio zapisane wartości. [3 pkt]

2) Należy także zapisać listę produktów za pomocą bazy SQLite. Stworzyć co najmniej jedną tabelę przechowującą wszystkie wartości wypisane w liście (nazwa produktu, cena, ilość i oznaczenie czy kupione). [7 pkt]

3) (Opcjonalne, na 3 dodatkowe punkty) Utworzyć Content Providera umożliwiającego dostęp do znajdujących się w bazie danych informacji.
