#include <iostream>
#include "./include/HashTable.h"
using namespace std;

int main()
{
    HashTable<int, std:: hash<int>, std:: equal_to<int>> table;
    cout << "Empty Table: " << endl << table << endl;
    cout << "Insert values 1,2,3,4" << endl;
    table.insert(1);
    table.insert(2);
    table.insert(3);
    table.insert(4);

    cout << "Table: " << endl << table << endl;

    table.erase(1);

    cout <<"Removed 1" << endl << table << endl;
    cout << "Contains: 2 ? " << table.contains(2) << endl;
    cout << "Contains with not containing value 8 ?" << table.contains(8) << endl;

    cout << "Rehash Table to try producing kollisions" << endl  << endl;
    table.rehash(2);

    cout << "Table rehashed: " << endl << table << endl;

    table.insert(1);
    table.insert(2);
    cout << "Added value 1 and allready containing value 2 " << endl << table << endl;

    table.insert(10);
    table.erase(2);
    cout << "Added 10 Removed 2: " << endl << table << endl;

    return 0;
}
