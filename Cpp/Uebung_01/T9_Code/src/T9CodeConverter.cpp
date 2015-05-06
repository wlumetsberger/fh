#include "T9CodeConverter.h"

/// CONSTRUCTOR DESTRUCTOR ///
T9CodeConverter::T9CodeConverter(string dictionaryPath, string textPath):dictPath(dictionaryPath), textPath(textPath){
    // initialize Dictionary and Text
    this->initT9CodeBase();
    this->loadDictionary();
    this->loadText();
}

T9CodeConverter::~T9CodeConverter(){
    // nothing TODO here
}

/**
* load dictionary into basic and extended Dictionary
**/
void T9CodeConverter::loadDictionary(){
    ifstream file;
    file.open(this->dictPath);
    if(file.fail()){
        cerr << "Cannot Load Dictionary" << flush;
        return;
    }
    string line;
    while (file >> line){
        string help = normalize(line);
        // insert word into advanced dictionary ordered by lenght
		dictionaryAdvanced[help.size()].insert(help);
        // insert word into basic dictionary
        dictionary.insert(help);
    }
    cout << "Dictionary Loaded with: " << this->dictionary.size() << " entries \n" << flush;
    file.close();
}
/**
* load the 20000 words text
**/
void T9CodeConverter::loadText(){
    ifstream fileStream;
    fileStream.open(this->textPath);
    if(fileStream.fail()){
        cerr << "Cannot Load Text " << flush;
        return;
    }
    string line;
    int count=0;
    while (fileStream >> line){
        string help = normalize(line);
        //if(this->dictContainsFast(help)){ do not check if word is in dictionary will fast-up loading text
            map<string,int>::iterator it = wordsCount.find(help);
            if(it == wordsCount.end()){
                wordsCount.insert(pair<string,int>(help,1));
            }else{
                (*it).second += 1;
            }
            count ++;
        //}
    }
    cout << "Text Loaded with: " << count << " entries \n" << flush;
    fileStream.close();
}
/**
* initialize a map with T9Code mapping
**/
void T9CodeConverter::initT9CodeBase(){
    t9CodeBase[2] = "abc";
    t9CodeBase[3] = "def";
    t9CodeBase[4] = "ghi";
    t9CodeBase[5] = "jkl";
    t9CodeBase[6] = "mno";
    t9CodeBase[7] = "pqrs";
    t9CodeBase[8] = "tuv";
    t9CodeBase[9] = "wxyz";
}
/**
* function to check if a word is in dictonary optimized
**/
bool T9CodeConverter::dictContainsFast(string word){
    set<string> help = dictionaryAdvanced[word.size()];
    set<string>::const_iterator got = help.find(word);
    if(got != help.end()){
        return true;
    }
    return false;

}
/**
* function to check if a word is in dictionary
**/
bool T9CodeConverter::dictContains(string word){
    set<string>::const_iterator got = dictionary.find(word);
    if(got != dictionary.end()){
        return true;
    }
    return false;
}
/**
* function to convert a Character into a Number
* e.g. 'a' -> 2
**/
int T9CodeConverter::charToDigit(char c)const{
    int value;
    for_each(t9CodeBase.begin(), t9CodeBase.end(), [&c, &value](pair<int,string> entry){
        if(entry.second.find(tolower(c),0) != std::string::npos){
            value=entry.first;
        }
    });
    return value;
}
/**
* function to convert a T9Digit into a String
* e.g. 2 -> "abc"
**/
string T9CodeConverter::digitToString(int digit) const{
    string value;
    for_each(t9CodeBase.begin(), t9CodeBase.end(), [&digit, &value](pair<int,string> entry){
        if(entry.first == digit){
            value.append(entry.second);
        }
    });
    return value;
}
/**
* function that returns all possible combination of strings for a given T9Code
**/
set<string> T9CodeConverter::numberToStrings(string number) const{
    list<string> wordsList;
    set<string> words;
    set<string> tmp;
    set<string> tmpWords;
    int sizeOfWord = number.size();

    for_each(number.begin(), number.end(), [&wordsList, this](char c){
        wordsList.push_back(digitToString(atoi(&c)));
    });
    for_each(wordsList.begin(), wordsList.end(),[&words, &tmp, &tmpWords, &sizeOfWord](string chars){
        for_each(chars.begin(),chars.end(),[ &tmp, &tmpWords](char letter){
            if(tmpWords.empty()){
                tmp.insert(&letter);
            }else{
                for_each(tmpWords.begin(), tmpWords.end(),[&tmp, &letter](string word){
                    tmp.insert(word.append((&letter)));
                });
            }
        });
        for_each(tmp.begin(),tmp.end(),[&words, &tmp, &tmpWords, &sizeOfWord](string word){
            if(word.size() == sizeOfWord){
                words.insert(word);
            }
            tmpWords.insert(word);
        });
    });
    return words;
}
/**
* function to convert a word into a T9Code
**/
string T9CodeConverter::wordToNumber(string word)const{
    string retVal;
    for_each(word.begin(), word.end(),[&retVal,this](char c){
        retVal.append(to_string(charToDigit(c)));
    });
    return retVal;
}
/**
* function to convert any T9Code to words
**/
set<string> T9CodeConverter::numberToWords(string number){
    set<string> values = this->numberToStrings(number);
    set<string> finalValues;
    for_each(values.begin(), values.end(),[&finalValues,this](string word){
        if(this->dictContains(word)){
            finalValues.insert(word);
        }
    });
    return finalValues;
}
/**
* function to Convert any T9Code and Use a Dictionary sorted by length
**/
set<string> T9CodeConverter::numberToWordsByLength(string number){
    set<string> values = this->numberToStrings(number);
    set<string> finalValues;
    for_each(values.begin(), values.end(),[&finalValues,this](string word){
        if(this->dictContainsFast(word)){
            finalValues.insert(word);
        }
    });
    return finalValues;
}
/**
* function to convert any T9Code to words and words
* where the generated word is in prefix
**/
set<string> T9CodeConverter::numberPrefixToWords(string number){
    set<string> values = this->numberToStrings(number);
    set<string> finalValues;
    for_each(values.begin(), values.end(),[&finalValues,this](string word){
        if(this->dictContainsFast(word)){
            finalValues.insert(word);
        }
    });
    for(int i=number.size(); i<dictionaryAdvanced.size(); i++){
        set<string> help = dictionaryAdvanced[i];
        for_each(help.begin(), help.end(), [&finalValues,&number](string word){
            set<string>::const_iterator got = finalValues.find(word.substr(0,number.size()));
            if(got != finalValues.end()){
                finalValues.insert(word);
            }
        });
    }
    return finalValues;
}
/**
* function convert T9Code to possible words and sort by count of an text
**/
vector<string> T9CodeConverter::numberPrefixToSortedWords(string number){
    set<string> values = this->numberPrefixToWords(number);
    multimap<int,string, greater_equal<int>> sortValues;
    vector<string> finalValues;
    // loop through generated words and check frequency
    // inserting value in a multimap ensures values get inserted sorted desc by given comperator greater_equal
    for_each(values.begin(), values.end(), [&sortValues, this](string s){
        map<string,int>::const_iterator got = this->wordsCount.find(s);
        if(got == this->wordsCount.end()){
            // if loaded text does not contain word frequency is 0
            sortValues.emplace(0,s+"Frequency: 0");
        }else{
            sortValues.emplace(((*got).second),s + "Frequency: "+to_string((*got).second) );
        }
    });
    for_each(sortValues.begin(), sortValues.end(), [&finalValues](pair<int,string> entry){
        finalValues.push_back(entry.second);
    });
    return finalValues;
}
