# Spell Checker

Spell checker allows to check word whether it is written correctly or not.
If there is some kind of mistake, spellchecker will suggest a list 
of possible words, which could fix the mistake(s).

Allows to add dictionaries (as simple associative containers) and
user data (defined words) in order to get separate suggestions: one from the
dictionary and another from the user defined words set. 

## Example

The following lines of code shows, how actually spell checker  
could be used in a particular java program.

### How to load dictionaries

```java
HashMap<String, Long> dictData = new HashMap<>();
HashSet<String> userData = new HashSet<>();

try {
    DictionaryLoader.loadDefaultDict(PATH_TO_THE_DICTIONARY, dictData);
    DictionaryLoader.loadCustomDict(PATH_TO_THE_USER_DEFINED_DICTIONARY, dictData, userData);
} catch (FileNotFoundException e) {
    e.printStackTrace();
}
```

**PATH_TO_THE_DICTIONARY** - string path, could be used default 
("resource/dict-english-default.txt") as an example  

**PATH_TO_THE_USER_DEFINED_DICTIONARY** - string path to the user defined list of word, 
could be used default ("resource/dict-english-custom.txt") as an example  
 
### How to create SpellChecker
 
```java
ISpellChecker spellChecker = new SpellChecker();
spellChecker.addDictionary(dictData);
spellChecker.addUserData(userData);
```

We add our dictionary data in the spellChecker internal search tree. 
Also we add user data to be able sort suggestions from dictionary. It could 
be useful if the user has his own (custom) words, which he want see separately.    

### Also customize our params

```java
spellChecker.setMaxEditOperationCount(3);
spellChecker.setMaxSuggestionsCount(8);
```

We can set the max number of editing operations, which could be used to get
the suggested word from the input word. Also we can limit the number of suggested 
words, which will be returned by getSuggestion() function.  

### How to check the spelling of a concrete word

```java
String word = "swimmer";
boolean correct = spellChecker.contains(word);
ISpellChecker.CheckResult result = spellChecker.getSuggestions(word);

System.out.println("Word: " + word + " correct: " + correct);

if (result.fromDict().size() > 0) {
    System.out.println("Possible suggestions from dictionary");
    for (String s : result.fromDict()) {
        System.out.println(s);
    }
} else {
    System.out.println("No suggestions from dictionary");
}

if (result.fromUserData().size() > 0) {
    System.out.println("Possible suggestions from user words");
    for (String s : result.fromUserData()) {
        System.out.println(s);
    }
} else {
    System.out.println("No suggestions from user words");
}
```

### Output of the program, listed bellow

```
Word: swimmer correct: true
Possible suggestions from dictionary
swimmer
simmer
swimmers
shimmer
skimmer
slimmer
summer
skimmed
No suggestions from user words
```

## Solution notes

* Uses Levenshtein distance function (matrix implementation)
* Uses prefix tree to store words in from the dictionary
* Uses hash map and has set to load actual data from the HHD
* Uses JUnit 4 for tests
* Allows only to load simple dictionaries
* Takes nearly 11 ms to find suggestions (edits: 3, suggestions: 10)

## How to build and run the application

This project was created in the JetBrains IntelliJ IDEA 2017 (java 9).
You can download the repository and open it as a simple project or import it. To run
console application you need to execute Application.Main class. 

## About

This project was created and developed by [Egor Orachyov](https://github.com/EgorOrachyov)
as a part of the JetBrains Summer Internship 2019 project: Support for native language in the IntelliJ IDEA. 