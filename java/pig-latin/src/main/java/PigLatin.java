import java.util.StringTokenizer;

public class PigLatin {
    /**
       Class to translate english phrases to pig latin

       Basic rules of pig latin:

       Rule 1: If a word begins with a vowel sound, add an "ay" sound
       to the end of the word.

       Rule 2: If a word begins with a consonant sound, move it to the
       end of the word, and then add an "ay" sound to the end of the
       word.

     */
    private static final String ay = "ay";
    private static final char[] vowels = {'a', 'e', 'i', 'o', 'u'};

    /**
       Translate an english phrase into pig latin
       @param englishPhrase the phrase to be translated
       @return the translated pig latin
     */
    public static String translate(String englishPhrase) {

        String translatedPhrase = "";
        String[] englishPhraseArray = englishPhrase.split(" ");

        for (int w = 0; w < englishPhraseArray.length; w++) {
            String word = englishPhraseArray[w];
            // Find the location of the first vowel in the word
            int firstVowel = word.length();
            for (int i = 0; i < vowels.length; i++) {
                int vowelInPhrase = word.indexOf(vowels[i]);
                if (vowelInPhrase > -1) {
                    firstVowel = Math.min(firstVowel, vowelInPhrase);
                }
            }

            String translatedWord = "";
            if (firstVowel == 0) {
                // leading vowel, apply rule 1
                translatedWord = translateLeadingVowel(word);
            } else if (firstVowel == 1) {
                // single leading consonants, with special case processing.
                translatedWord = translateSingleLeadingConsonant(word);
            } else {
                // leading consonant requires additional special case processing.
                translatedWord = translateLeadingConsonantGroup(word, firstVowel);
            }
            translatedPhrase = translatedPhrase + " " + translatedWord;
        }
        // If we only had a single word in the phrase, then the word
        // loop added a lead space. Strip it off before returning.
        return translatedPhrase.trim();
    }

    /**
       Translation rule when there is a leading vowel
       @param word to be tranlated
       @return the translated pig latin
     */
    private static String translateLeadingVowel(String word) {
        return applyTranslationRuleOne(word);
    }

    /**
       Translation rule when there is a single leading consonant
       @param word the phrase to be tranlated
       @return the translated pig latin

       Words with a single leading consonant are generally treated by
       rule 2 above, except when the word starts with 'qu', which are
       treated as a group.
    */
    private static String translateSingleLeadingConsonant(String word) {
        final int firstVowel = 1;
        String translatedWord = "";
        if (word.charAt(0) == 'q') {
            // q always followed by u, treat qu as a group of 2
            translatedWord = translateLeadingConsonantGroup(word, 2);
        } else {
            translatedWord = applyTranslationRuleTwo(word, firstVowel);
        }
        return translatedWord;
    }

    /**
       Translation rule when there are multiple leading consonants
       @param word the phrase to be tranlated
       @param the position of the first vowel
       @return the translated pig latin

       When a word starts with with y or x followed by a consonant,
       then y and x have a vowel sound, and the word is treated as if
       it started with a vowel.

       When the first vowel of a word is u preceeded by a q, then the
       u is considered part of tho consant group.
     */
    private static String translateLeadingConsonantGroup(String word, int firstVowel) {
        String translatedWord = "";
        if (word.charAt(0) == 'y' || word.charAt(0) == 'x') {
            // y and x are actually a vowel sound, not a consonant.
            translatedWord = applyTranslationRuleOne(word);
        } else if (word.charAt(firstVowel) == 'u' && word.charAt(firstVowel-1) == 'q') {
            translatedWord = applyTranslationRuleTwo(word, firstVowel + 1);
        } else {
            translatedWord = applyTranslationRuleTwo(word, firstVowel);
        }
        return translatedWord;
    }

    /**
       Apply translation rule 1
       @param word to be tranlated
       @return the translated pig latin

       Rule 1: If a word begins with a vowel sound, add an "ay" sound
       to the end of the word.
     */
    private static String applyTranslationRuleOne(String word) {
        return word + ay;
    }

    /**
       Apply translation rule 2
       @param word the phrase to be tranlated
       @param the position of the first vowel
       @return the translated pig latin

       Rule 2: If a word begins with a consonant sound, move it to the
       end of the word, and then add an "ay" sound to the end of the
       word.
     */
    private static String applyTranslationRuleTwo(String word, int firstVowel) {
        return word.substring(firstVowel) + word.substring(0, firstVowel) + ay;
    }
}
