# Word prediciton algorithm

## Problem

Algorithm for prediction of word endings. 
Example implementation in a mobile keyboard:

<img style="margin-left: 35%; width:30%;" src="https://user-images.githubusercontent.com/120416913/219973743-0dbaf256-002e-4a8a-889c-085730388014.png" alt="Group 304">

</br>

## Algorithm
The algorithm is based on a directed tree. Any path in the tree starting at a root and ending at any vertex (it doesn't have to be a leaf) is a word or subword.

Each vertex (Node.java) stores:
```
public int id;
public int parent;
public char sign;
public ArrayList<Integer> neighbours;
public boolean isEndOfWord;
public int numberOfUses;
public ArrayList<PairP> maxSubtree;
```
The ```maxSubtree``` list stores the 3 most popular words in the subtree.

Algorithm steps:
- building a tree (from a dictionary or from a simple, self-made database)
- After loading the training data, the coefficients are completed. We go to the vertex representing the last letter of the word and increment the value, then going to the root we update the top 3 at each vertex.
- To read which vertex represents the last letter we go from the root according to each subsequent letter.
- To read the prediction, we go to the vertex representing the last letter of the input data and read the top 3.
- Updating the model with user feedback. Increments the number of occurrences of each user-typed word that appears in the dictionary.

Time complexity:
- Building a tree: <b>O(S)</b>, S - the sum of the lengths of the words in the dictionary;
- Training the algorithm: <b>O(T)</b>, T - the sum of the lengths of the words in the training data;
- Predictiong words: <b>O(1)</b>;
- Increasing word: <b>O(n * k)</b>, n - number of words, k - word length.

<br />

A part of tree:

<img src="https://user-images.githubusercontent.com/120416913/219974992-c5582142-2a46-4cdb-b2d4-1aaef6a0524f.png" alt="Group 304" style="margin-left: 35%; width:50%;">

## Collecting data
### Dictionary
To download the entire dictionary of the Polish language, I used the python library - selenium. The downloaded dictionary has about 200,000 words.
### Training data
The best training data in this case are simple conversations, not books or articles.
Where to get normal human conversations from?

Messenger would be cool, but it's hard :((

So, I visited youtube and downloaded the audio of the 6 most popular videos in Poland (I used a python library converting audio to text), thanks to which I acquired 24,000 learning words :))
They were lifestyle movies, so they contained a lot of plain popular language. I could teach the tree with data from literary works, then predictions would be made based on, for example, Pan Tadeusz by Mickiewicz.

## Future
### API
Creating an API is not a problem, because the time complexity of the algorithm allows you to save the tree in the database (~195,000 records) and operate on the database as on the PredictionTree class. To implement the solution as an API, you can use one of the frameworks, e.g. flask, spring.

### Predicting next words
