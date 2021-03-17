# Text Transformer

Transform RSS feed items as in the following description

The program takes a URL or file, transforms the text of them, then writes
it to a file or the standard output.
- Input A: A URL of RSS
- Input B: A CSV file
- Convert A: Trim each title and body if the length exceeds 10 characters.
- Convert B: Replace the specified words. e.g: from=default, to=Default, Inc.
- Output A: Print the result to the standard output
- Output B: Print the result to the specified file


## General idea of the solution

1. Parse the command line arguments.
   For example `java RssReader --input=http://tech.com/rss --convert=cut --output=result.txt`
   </br> where `--input` is a file name or URL `--convert` is a convert operation `--output` is a text file output
   </br> `input` is mandatory
   </br> `convert` is mandatory (allowing multiple operators)
   </br> `output` can be optional, default is standard output
2. If the `input` is URL, then read the data from that URL and parse it as RSS feed
   <br/>If the `input` is file, then read the data from that file and parse it as RSS feed
3. If the `convert` or `c` is "cut", then trim each title and body of the RSS item to length of 10 chars
   <br/> If the `convert` or `c` is "replace/a/b/", then replace each a by b in the title and body of the RSS item
   <br/> If the `convert` or `c` is "cut,convert", then replace each "default" to "Default, Inc." in the title and body of the RSS item and trim them by 10
4. If the `output` exists, then write the final result to the file.
   <br/>Otherwise write the final result to standard output

## Class diagram

![text-converter-diagram](https://i.imgur.com/ZEBWsU8.png)
## Installation

Clone the project from git repository
```bash
https://github.com/daniil-morozov/text-transformer.git
```

## Usage

Run the shadow jar file

## Prerequisites

JDK 8/gradle/

Go to the cloned repo folder
```bash
# Build the project
gradle build
# Change dir
cd build/libs/
# For help run
java -jar text-transformer-1.0-SNAPSHOT-all.jar setup-app --help # this will tell you how to use the app arguments
# Run the fat jar. Examples
java -jar text-transformer-1.0-SNAPSHOT-all.jar setup-app -i <input.txt>  -c <replace/aaa/bbb/> -o=<output.txt> # text input, text output
# or
java -jar text-transformer-1.0-SNAPSHOT-all.jar setup-app -i <http://example.com>  -c <replace/aaa/bbb/> -o=<output.txt> # URL input, text output
# etc
```

## Testing

### There are unit and integration tests

```bash
# For tests run
gradle test # from root project folder
```

## How the app works

### Setup app command

The facade part of the app, parses/validates the arguments and controls the run 

### Command parser

Parses the commands (cut, replace, etc)

### Input/Output parser

Parses the inputs/outputs (file, url)

### Converter Command

Interface for the convert A / convert B

#### Text Converter

Implementation of converter based on converting strategies

#### Trim / Replace Strategy

Implementations of concrete convert strategies: cut or replace

### App Result Writer Impl

Implementation of result writer: writes to the file or standard output

## Libraries used

`com.github.rvesse:airline` for command-line arguments https://rvesse.github.io/airline/ <br/>
`com.apptastic:rssreader` to parse RSS https://github.com/w3stling/rssreader

## Example output

`java -jar text-transformer-1.0-SNAPSHOT-all.jar setup-app -i https://stackoverflow.com/feeds -c replace/Python/Python-Python/ -c cut` 

```
JS Code in<p>I'm jusHow do you<p>I am suVisual Stu<p>I creatPython-Pyt<p>code:</saving sta<p>i have File uploa<p>For thiDo service<p>I was lHosting a <p>What isHow to ref<p>Hi we mGet min va<p>Hei,</pHow do I d<p>I'm makHow to cal<p>I am trIs there a<p>I have Failed to <p>How do How to cha<p>I writeHow to get<p>I have Switching <p>I am neLINQ metho<p><strongMultiple D<p>I have Lookup mul<p>Issue: Wpf or Asp<p>I have How to acc<p>I have Why does p<p>It's sohow to pro<p>We haveHow can I <p>I am abFortran ru<p>This ishow make m<p>i'm doiCannot rea<p>When thWhat LINQ <p>What LISwift 3: H<p>I have 
```
<br/>In the result above you will discover all "Python" words are replaced with "Python-Pyt" and all sentences are trimmed by size of 10 chars

## Problems:

I don't have the strong validation rules against command-line args
No hardcore testing with wrong file formats, URLs