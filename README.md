# Spell Checker

Simple API with console application written in Java 9, which
provides the spelling of a concrete word and provides possible 
suggestions, how to write this word correctly, whether it is written
with some kind of a mistake.

## API

## Examples

The following lines of code shows, how actually SpellChecker 
could be used in a particular java program:

```$java

```

```$java

String word = "company";
SpellChecker speller = new SpellChecker(data, userData);
CheckResult result = speller.getSuggestions(word);
...

```

## Concept

The following steps shows how the actually the spell checker works 
under the hood (note: that this info is actual only for current 
implementation, the other one could be simply created on top of that):

* Loading the dictionary form HDD - simple table with pairs: English word and its usage
* Creating the prefix tree for our dictionary with the words
* 
*
*
*

## About

This project was created and developed by [Egor Orachyov](https://github.com/EgorOrachyov)
as a part of the JetBrains Summer Internship 2019 project: Support of native language for IntelliJ IDEA. 