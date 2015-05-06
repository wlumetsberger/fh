#include <iostream>
#include <string>
#include <map>
#include <algorithm>
#include <T9CodeConverter.h>
#include <unordered_set>
#include <chrono>
#include <istream>
#include <iterator>
using namespace std;

int main(){
    T9CodeConverter* t = new T9CodeConverter("de_neu.dic", "faust1.txt");

    string s=  "32878";

    auto start = std::chrono::high_resolution_clock::now();
    set<string> c = t->numberToWordsByLength(s);
    auto finish = std::chrono::high_resolution_clock::now();
    for_each(c.begin(), c.end(),[](string word){
        cout << "Found Word: " << word << "\n" << flush;
    });
    cout << "SEARCH IN DICT LOADED AS SET DURATION: "  << std::chrono::duration_cast<std::chrono::nanoseconds>(finish-start).count() << "ns" << endl << flush;

    start = std::chrono::high_resolution_clock::now();
    set<string> x = t->numberToWords(s);
    finish = std::chrono::high_resolution_clock::now();
    for_each(x.begin(), x.end(),[](string word){
        cout << "Found Word: " << word << "\n" << flush;
    });
    cout << "SEARCH IN DICT LOADED AS MAP GROUP BY LENGTH DURATION: "  << std::chrono::duration_cast<std::chrono::nanoseconds>(finish-start).count() << "ns" << endl << flush;

    start = std::chrono::high_resolution_clock::now();
    set<string> y = t->numberPrefixToWords(s);
    finish = std::chrono::high_resolution_clock::now();
    for_each(y.begin(), y.end(),[](string word){
        cout << "Found Word: " << word << "\n" << flush;
    });
    cout << "SEARCH WITH PREFIX IN DICT LOADED AS MAP GROUP BY LENGTH DURATION: "  << std::chrono::duration_cast<std::chrono::nanoseconds>(finish-start).count() << "ns" << endl << flush;

    start = std::chrono::high_resolution_clock::now();
    vector<string> freq = t->numberPrefixToSortedWords(s);
    finish = std::chrono::high_resolution_clock::now();
    for_each(freq.begin(), freq.end(),[](string word){
        cout << "Found Word: " << word << "\n" << flush;
    });
    cout << "SEARCH WITH PREFIX IN DICT ORDER BY FREQUENCY IN TEXT: " << std::chrono::duration_cast<std::chrono::nanoseconds>(finish-start).count() << "ns" << endl << flush;
    return 0;
}
