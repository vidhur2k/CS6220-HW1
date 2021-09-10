# CS6220 Homework 1 (Problem 1 Option 1)

## How this Repo is Structured


## Setup
I used the `hadoop-docker` Docker image found [here](https://github.com/big-data-europe/docker-hadoop) to run a single node cluster. Running this program will require you to move some files from the host machine into the container.

There are two datasets here:

**WordCount**: 
I used the SMS Spam Collection Dataset (found [here](https://www.dt.fee.unicamp.br/~tiago/smsspamcollection/)) and the larger dataset was also a set of SMS collections but 100x the size.

**Earthquake Measurement**: 
This is a text file containing measurements of tremors along Earthquake-hotspots in the world. Remember that we're using two versions of the dataset. To prep the larger one, run `python3 earthquake/prep_large.py`. This will create a `Earthquake-large.txt` file to use as the larger dataset.

## JAR Files
### WordCount
1. **wc.jar**: This compiles the WordCount program with the default parameters.
2. **wc-100k.jar**: This compiles the WordCount program with a max input split size of 1000000.
3. **wc-500k.jar**: This compiles the WordCount program with a max input split size of 5000000.

### Earthquake
1. **eq.jar**: This is the Earthquake MapReduce program with the default parameters.