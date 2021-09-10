# CS6220 Homework 1 (Problem 1 Option 1)

## How this Repo is Structured
1. The `artifacts` directory contains the JAR files needed to run each variation of the two problems solved in this HW.
2. The `earthquake` directory contains the .txt dataset file, the Java file that implements the Earthquake Measurement Mapreduce program, and a python script that sets up the large dataset (it was too big to upload to Github.)
3. The `smsspamcollection` directory contains the .txt datasets file, the Java fille that implements the WordCount Mapreduce program, and a python script to remove unnecessary punctuation from the datasets.
4. The `outputs` directory contains the outputs of the SMS Wordcount and the Earthquake Measurement programs. Each subdirectory contains a `small.txt` and a `large.txt` that corresponds to the outputs obtained from running the program on the small and large datasets respectively.
5. The `screenshots` directory contains screenshots of the execution process for each variation of the problems.
6. `plots.ipynb` is the Jupyter Notebook used to produce the Seaborn plots that is seen in the Analytical Report.

## Setup
I used the `hadoop-docker` Docker image found [here](https://github.com/big-data-europe/docker-hadoop) to run a single node cluster. Running this program will require you to move some files from the host machine into the container. However, *you can use hadoop from the host machine directly if you have it installed.*

There are two datasets here:

**WordCount**
I used the SMS Spam Collection Dataset (found [here](https://www.dt.fee.unicamp.br/~tiago/smsspamcollection/)) and the larger dataset was also a set of SMS collections but 100x the size.

**Earthquake Measurement**
This is a text file containing measurements of tremors along Earthquake-hotspots in the world. Remember that we're using two versions of the dataset. To prep the larger one, run `python3 earthquake/prep_large.py`. This will create a `Earthquake-large.txt` file to use as the larger dataset.

#### JAR Files
**WordCount**
1. **wc.jar**: This compiles the WordCount program with the default parameters.
2. **wc-100k.jar**: This compiles the WordCount program with a max input split size of 1000000.
3. **wc-500k.jar**: This compiles the WordCount program with a max input split size of 5000000.

**Earthquake Measurement**
1. **eq.jar**: This is the Earthquake MapReduce program with the default parameters.
2. **eq-100k.jar**: This is the Earthquake MapReduce program with a max input split size of 1000000.
3. **eq-500k.jar**: This is the Earthquake MapReduce program with a max input split size of 5000000.

#### Directory Structure for HDFS
You'll need to move all datasets into the HDFS for use in the MapReduce programs. The structure is as follows:

1. `/user/wordcount/input/small` and `/user/wordcount/input/large` will contain the small and large SMS datasets respectively.
2. `/user/wordcount/output` will contain the outputs to the WordCount program on the small and large dataset.
3. `/user/earthquake/input/small` and `/user/earthquake/input/large` will contain the small and large Earthquake datasets respectively.
4. `/user/earthquake/output` will contain the outputs to the Earthquake Measurement program on the small and large dataset.

Run `hdfs dfs -mkdir -p <DIRECTORY>` and create each of the following and use `hdfs dfs -put <HOST_PATH_FOR_DATASET> <DFS DIRECTORY>` to add the files to their respective locations.