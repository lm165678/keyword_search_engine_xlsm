# keyword_search_engine_xlsm
[![Build Status](https://travis-ci.com/zhongjis/keyword_search_engine_xlsm.svg?branch=master)](https://travis-ci.com/zhongjis/keyword_search_engine_xlsm)

This is a simple keyword matching system originally built for [Seedsprint](https://www.seedsprint.com). Now it is just a personal project for my own practice.

## Disclaimer:
### This project is for my own Java learning purpose. I am trying to make it a better contributor-friendly project and feel free to let me know what you think!
### This project is currently under reconstruction to utilize Spring Framework. This move aims to offer a better usr/developer experience. More information will be updated soon.  

## Requirements:
### 1: MongoDB
- should have a db called **workdata_seedsprint** 
- should have a collection called **fullname_skills** 
- should have a collection called **skill_fullnames**
- should have a collection called **skill_tdidf**

**IMPORTANT**: please make sure all collections are empty to avoid data contamination.

### 2: Gradle
- this program is built with Gradle. if you need to install the tool. you can use homebrew:
> brew install Gradle <br>
- if you want to run the program, please navigate to the project root folder and run:
> gradle run

**IMPORTANT**: I will suggest using IntelliJ to run the program. So you will not need to set gradle up for input taking.

### 3: Source File
- please put all source files under ***/src/main/resources*** 
- due to the format limitation of .xlsx file, please convert them to .xlsm. About how to do this. please check this [link](https://support.office.com/en-us/article/save-a-workbook-in-another-file-format-6a16c862-4a36-48f9-a300-c2ca0065286e) 

## Express Setup:
> git clone https://github.com/zhongjis/keyword_search_engine_xlsm.git <br>
> cd keyword_search_engine_xlsm <br>
> gradle run <br>

## How to use
### Option 1 - no DB required
- if you want to see output in real-time without saving them, you can type **n** in the program menu.
### Option 2 - with MongoDB
- if you want use Mongodb storing all the data, you can type **y** int the program menu. 
- MongoDB setup requirements please refer back to above.
- use the any db visualization tool to see output in mongodb. eg: [MongoDB Compass](https://www.mongodb.com/products/compass)

#### Something about the tfidf result
- ***bigger the tfidf is, means the skill is rarer***. I will add a ranking program later to rank all the term/skills.

## Update & Known Issues
- this item has been moved to [here](https://github.com/zhongjis/keyword_search_engine_xlsm/wiki/Update-Logs-and-Known-Issues)
