# keyword_search_engine_xlsm
[![Build Status](https://travis-ci.com/zhongjis/keyword_search_engine_xlsm.svg?branch=master)](https://travis-ci.com/zhongjis/keyword_search_engine_xlsm)

This is a simple keyword matching system built for [Seedsprint](https://www.seedsprint.com). 

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

## Update & Known Issues
- this item has been moved to [here](https://github.com/zhongjis/keyword_search_engine_xlsm/wiki/Update-Logs-and-Known-Issues)
