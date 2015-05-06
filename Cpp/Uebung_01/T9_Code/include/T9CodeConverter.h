#ifndef T9CODECONVERTER_H
#define T9CODECONVERTER_H
#include<iostream>
#include<map>
#include<algorithm>
#include<string>
#include<set>
#include<locale>
#include<list>
#include<fstream>
#include<utils.hpp>


using namespace std;
class T9CodeConverter
{
    private:
        map<int,string> t9CodeBase; // store T9Code map
        map<int,set<string>> dictionaryAdvanced; // dictionary entries grouped by lenght
        set<string> dictionary; // dictionary basic
        map<string,int> wordsCount; // word and the frequency occuring in the loaded text

        string dictPath; // path for dictionary
        string textPath; // path for text

        virtual void initT9CodeBase();
        virtual void loadDictionary();
        virtual void loadText();

        virtual bool dictContains(string word);
        virtual bool dictContainsFast(string word);
        inline virtual map<int,string> getT9CodeBase() const{return this->t9CodeBase;}

    public:
        T9CodeConverter(string dictionaryPath, string textPath);
        virtual ~T9CodeConverter();

        inline virtual string getDictionaryPath()const{return this->dictPath;}
        inline virtual string getTextPath()const{return this->textPath;}

        virtual string digitToString(int digit) const;
        virtual set<string> numberToStrings(string number) const;
        virtual string wordToNumber(string word)const;
        virtual int charToDigit(char c)const;
        virtual set<string>numberToWords(string number);
        virtual set<string>numberToWordsByLength(string number);
        virtual set<string>numberPrefixToWords(string number);
        virtual vector<string>numberPrefixToSortedWords(string number);
};

#endif // T9CODECONVERTER_H
