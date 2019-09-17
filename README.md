# keyword_search_seedsprint
[![Build Status](https://travis-ci.com/zhongjis/keyword_search_seedsprint.svg?branch=master)](https://travis-ci.com/zhongjis/keyword_search_seedsprint)

This is a simple keyword matching system for [Seedsprint](https://www.seedsprint.com). 

## Express Setup:
### Run following commands in bash under the folder as you like:
> git clone https://github.com/zhongjis/keyword_search_engine_xlsm.git <br>
> cd keyword_search_engine_xlsm <br>
> gradle run <br>

## Set Up Details:
### 1: MongoDB (Currently not used)
- should have a db called **workdata_seedsprint** 
- should have a collection called **fullname_skills** 

### 2: Gradle
- this program is built with Gradle. if you need to install the tool. you can use homebrew:
> brew install Gradle <br>
- if you want to run the program, please navigate to the project root folder and run:
> gradle run

### 3: Source File
- please put all source files under ***/src/main/resources*** 
- due to the format limitation of .xlsx file, please convert them to .xlsm. About how to do this. please check this [link](https://support.office.com/en-us/article/save-a-workbook-in-another-file-format-6a16c862-4a36-48f9-a300-c2ca0065286e) 

## Example Output
- all skills will be printed in the console like

> [INFO] term: Big Six Non-O157 STEC Panels: tfidf 6.054439346269371 <br>
> [INFO] term: Six Sigma: tfidf 6.054439346269371 <br>
> [INFO] term: Project Management: tfidf 4.2626798770413155 <br>
> [INFO] term: Strategy: tfidf 4.2626798770413155 <br>
> [INFO] term: Mobile Devices: tfidf 6.054439346269371 <br>
> [INFO] term: Deal Structuring: tfidf 6.054439346269371 <br>
> [INFO] term: food safety modernization act: tfidf 6.054439346269371 <br>
> [INFO] term: Chemietechnik: tfidf 6.054439346269371 <br>

**bigger the tfidf is, means the skill is rarer**. I will add a ranking program later to rank all the term/skills.

## Known Issues
1. temporary file such as ~$worddata.xlsm will also be considered as valid input file.

## Update:
### August 17th, 2019
- House cleaning

### August 18th, 2019
- fixed a bug that XLSXHandler cannot get the right fullName and skill columns

### August 14th, 2019
- now the program will find all .xslm files under resources folder.
- now you will see more prompts and know what's going on in the program. this also helps debugging.

### August 9th, 2019
- now the tfidf calculation should be working
- Known Issue: HashMap dict_skill_fullNames is not correctly generated. the fullNames list is never updated when key exists. (Solved)

### August 7th, 2019
- every parts are ready except tfidf calculation 
- cuz this is the first version, all parts are hard coded. 
- database is not used, ignore database error for now.
- Thank you for your patience:)
