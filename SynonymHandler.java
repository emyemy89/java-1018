package lab2;
import java.io.*; // FileReader, BufferedReader, PrintWriter,


class SynonymHandler
{
// readSynonymData reads the synonym data from a given file
// and returns the data as an array
public static String[] readSynonymData (String synonymFile)
throws IOException
{
BufferedReader reader = new BufferedReader(
new FileReader(synonymFile));
int numberOfLines = 0;
String synonymLine = reader.readLine();
while (synonymLine != null)
{
numberOfLines++;
synonymLine = reader.readLine();
}
reader.close();
String[] synonymData = new String[numberOfLines];
reader = new BufferedReader(new FileReader(synonymFile));
for (int i = 0; i < numberOfLines; i++)
synonymData[i] = reader.readLine();
reader.close();
return synonymData;
}
// writeSynonymData writes a given synonym data to a given
// file
public static void writeSynonymData (String[] synonymData,
String synonymFile) throws IOException
{
PrintWriter writer = new PrintWriter(synonymFile);
for (String synonymLine : synonymData)
writer.println(synonymLine);
writer.close();
}
// synonymLineIndex accepts synonym data, and returns the
// index of the synonym line corresponding to a given word.
// If the given word is not present, an exception of
// the type IllegalArgumentException is thrown.
private static int synonymLineIndex (String[] synonymData,
String word) throws IllegalArgumentException
{
int delimiterIndex = 0;
String w = "";
int i = 0;
boolean wordFound = false;
while (!wordFound && i < synonymData.length)
{
    delimiterIndex = synonymData[i].indexOf('|');
    //get the first word
    w = synonymData[i].substring(0, delimiterIndex).trim();
    if (w.equalsIgnoreCase(word))
    wordFound = true;
    else
    i++;
}
if (!wordFound)
    throw new IllegalArgumentException(
    word + " not present");
return i;
}

// getSynonymLine accepts synonym data, and returns
// the synonym line corresponding to a given word.
// If the given word is not present, an exception of
// the type IllegalArgumentException is thrown.
public static String getSynonymLine (String[] synonymData,
String word) throws IllegalArgumentException
{
int index = synonymLineIndex(synonymData, word);
return synonymData[index];
}

// addSynonymLine accepts synonym data, and adds a given
// synonym line to the data.
public static String[] addSynonymLine (String[] synonymData,
String synonymLine)
{
String[] synData = new String[synonymData.length + 1];
for (int i = 0; i < synonymData.length; i++)
synData[i] = synonymData[i];
synData[synData.length - 1] = synonymLine;
return synData;
}

// removeSynonymLine accepts synonym data, and removes
// the synonym line corresponding to a given word.
// If the given word is not present, an exception of
// the type IllegalArgumentException is thrown.
public static String[] removeSynonymLine (String[] synonymData,
String word) throws IllegalArgumentException
{
// add code here
// Determine index of the synonym line corresponding to the given word.
    int index = synonymLineIndex(synonymData, word);
    String[] synData = new String[synonymData.length - 1];
    // Copy elements before the found index
    for (int i = 0, j = 0; i < synonymData.length; i++) {
        if (i != index) {
            synData[j++] = synonymData[i];
        }
    }
    return synData;
}

// getSynonyms returns synonyms in a given synonym line.
private static String[] getSynonyms (String synonymLine)
{
// add code here
// Split the synonym line into parts based on the pipe ('|') character
    String[] parts = synonymLine.split("\\|");
// If there are synonyms (parts[1] exists), split is based on the comma ',' character
    if (parts.length > 1) {
        String[] synonyms = parts[1].split(",");
        // Trim each synonym to create a new array
        for (int i = 0; i < synonyms.length; i++) {
        synonyms[i] = synonyms[i].trim();
        } 
    return synonyms;
    } else {
    // If there are no synonyms, return an empty array
    return new String[0];
    }
}

// addSynonym accepts synonym data, and adds a given
// synonym for a given word.
// If the given word is not present, an exception of
// the type IllegalArgumentException is thrown.
public static void addSynonym(String[] synonymData, String word, String synonym)
        throws IllegalArgumentException {
    // Determine index of the synonym line corresponding to the given word.
    int index = synonymLineIndex(synonymData, word);
    // Get existing synonym line or create a new one if the word is not found
    String existingSynonymLine;
        if (index != -1) {
        existingSynonymLine = synonymData[index];
        } else {
        existingSynonymLine = word + " |";
        }
    // Get existing synonyms in the synonym line
    String[] synonyms = getSynonyms(existingSynonymLine);
    // Check if the given synonym already exists
    boolean synonymExists = false;
    for (String existingSynonym : synonyms) {
        if (existingSynonym.trim().equalsIgnoreCase(synonym.trim())) {
            synonymExists = true;
            break;
        }
    }
    if (synonymExists) {
        throw new IllegalArgumentException("Synonym already exists for the given word.");
    }
    // Add the new synonym to the existing list of synonyms
    String[] newSynonyms = new String[synonyms.length + 1];
    System.arraycopy(synonyms, 0, newSynonyms, 0, synonyms.length);
    newSynonyms[synonyms.length] = synonym;
    // Update the synonym line in the synonymData array
    if (index != -1) {
        synonymData[index] = word + " | " + String.join(", ", newSynonyms);
    } else {
        // If the word is not found, add a new synonym line
        String[] newSynonymData = new String[synonymData.length + 1];
        System.arraycopy(synonymData, 0, newSynonymData, 0, synonymData.length);
        newSynonymData[synonymData.length] = word + " | " + String.join(", ", newSynonyms);
        synonymData = newSynonymData;
    }
}

// removeSynonym accepts synonym data, and removes a given
// synonym for a given word.
// If the given word or the given synonym is not present, an
// exception of the type IllegalArgumentException is thrown.
// If there is only one synonym for the given word, an
// exception of the type IllegalStateException is thrown.
public static void removeSynonym(String[] synonymData, String word, String synonym)
        throws IllegalArgumentException, IllegalStateException {
    // Determine index of the synonym line corresponding to the given word.
    // If the word is not present, throw an exception.
    int index = synonymLineIndex(synonymData, word);
    if (index == -1) {
        throw new IllegalArgumentException("Word not present.");
    }
    String existingSynonymLine = synonymData[index];
    String[] synonyms = getSynonyms(existingSynonymLine);
    // Determine index of the synonym to be removed.
    int synonymIndex = -1;
    for (int i = 0; i < synonyms.length; i++) {
        if (synonyms[i].trim().equalsIgnoreCase(synonym.trim())) {
            synonymIndex = i;
            break;
        }
    }
    if (synonymIndex == -1) {
        throw new IllegalArgumentException("Synonym not present for the given word.");
    }
    // If there is only one synonym, throw an exception.
    if (synonyms.length == 1) {
        throw new IllegalStateException("Can not remove the last synonym for the given word.");
    }
    String[] newSynonyms = new String[synonyms.length - 1];
    for (int i = 0, j = 0; i < synonyms.length; i++) {
        if (i != synonymIndex) {
            newSynonyms[j++] = synonyms[i];
        }
    }
    synonymData[index] = word + " | " + String.join(", ", newSynonyms);
}

// sortIgnoreCase sorts an array of strings, using
// the selection sort algorithm
private static void sortIgnoreCase(String[] strings) {
    int n = strings.length;
    for (int i = 0; i < n - 1; i++) {
        // Find the minimum element
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            if (strings[j].compareToIgnoreCase(strings[minIndex]) < 0) {
                minIndex = j;
            }
        }
        // Swap 
        String temp = strings[minIndex];
        strings[minIndex] = strings[i];
        strings[i] = temp;
    }
}

// sortSynonymLine accepts a synonym line, and sorts
// the synonyms in this line
private static String sortSynonymLine(String synonymLine) {
    // Split the synonym line into parts based on the pipe ('|') character
    String[] parts = synonymLine.split("\\|");
    // If there are synonyms (parts[1] exists), split them based on the comma ',' character
    if (parts.length > 1) {
        // Split synonyms into an array
        String[] synonyms = parts[1].split(",");
        sortIgnoreCase(synonyms);
        // Join the sorted synonyms back into a string
        String sortedSynonyms = String.join(", ", synonyms);
        // Return the sorted synonym line
        return parts[0].trim() + " | " + sortedSynonyms;
    } else {
        // If there are no synonyms, return the original synonym line
        return synonymLine.trim();
    }
}

// sortSynonymData accepts synonym data, and sorts its
// synonym lines and the synonyms in these lines
public static void sortSynonymData(String[] synonymData) {
    // Sort the entire synonymData array based on the sorted synonym lines
    sortIgnoreCase(synonymData);
    // Sort each individual synonym line
    for (int i = 0; i < synonymData.length; i++) {
        synonymData[i] = sortSynonymLine(synonymData[i]);
    }
}
}