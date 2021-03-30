# Description

This tool generates the list of dependencies of your project. 

![CI](https://github.com/vladonemo/dependency-list/workflows/CI/badge.svg)

# Technologies

The following technologies have been used to write the tool

- Gradle
- Kotlin
- Klaxon
- YAML Azure Pipelines
- Spring Boot (without Web)
- Digraph parser

# Functionality

The tool currently supports:
**Input file formats**:
- *npm*
- *maven*
  **Output file formats**:
- *markdown* - the list of dependencies is written to a table in markdown format

## Maven collector

[Maven Dependency plugin](https://maven.apache.org/plugins/maven-dependency-plugin/) is used to generate the dependency tree in the [DOT format](https://en.wikipedia.org/wiki/DOT_(graph_description_language)). This is easy to parse with a third party java library.

## NPM Collector

The dependencies are collected using the [npm ls](https://docs.npmjs.com/cli/ls.html) command.

# Usage

## Collect from Maven projects

Generate the DOT file of your project:
1. navigate to the folder containing POM file
2. run the maven command: `mvn clean test-compile dependency:resolve dependency:tree -Dmaven.main.skip=true -Dmaven.test.skip=true -DoutputFile=maven.dg -DoutputType=dot -DappendOutput=false`

## Collect from NPM projects

1. navigate to the folder containing package.json file
2. run the following commands:
  - to get production dependencies: `npm ls --json --parseable --depth=0 --prod=true > npm.json`
  - to get development dependencies: `npm ls --json --parseable --depth=0 --dev=true > npm-dev.json`


## Define json configuration

Example:
```json
{
  "output": {
    "file": "output.md",
    "type": "markdown"
  },
  "toProcess": [
    {
      "group": "FE",
      "files": [
        {
          "path": "npm.json",
          "type": "npm"
        }
      ],
      "filter": {
        "exclude": [
          "package-name-to-exclude"
        ]
      }
    },
    {
      "group": "BE",
      "files": [
        {
          "path": "maven.dg",
          "type": "maven",
          "settings": {
            "scope": "compile"
          }
        }
      ],
      "filter": {
        "exclude": [
          "com.vladonemo:another"
        ]
      }
    },
    {
      "group": "FE test",
      "files": [
        {
          "path": "npm-test.json",
          "type": "npm"
        }
      ]
    },
    {
      "group": "BE test",
      "files": [
        {
          "path": "maven.dg",
          "type": "maven",
          "settings": {
            "scope": "test"
          }
        }
      ]
    }
  ]
}

```
 
## Run the tool

E.g. using gradle:
`gradlew bootRun --args="./input.json"`
