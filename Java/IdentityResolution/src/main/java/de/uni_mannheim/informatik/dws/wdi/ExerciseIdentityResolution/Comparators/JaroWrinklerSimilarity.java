package de.uni_mannheim.informatik.dws.wdi.ExerciseIdentityResolution.Comparators;

import com.wcohen.ss.Levenstein;

import de.uni_mannheim.informatik.dws.winter.similarity.SimilarityMeasure;


public class JaroWrinklerSimilarity extends SimilarityMeasure<String> {

	//private static final long serialVersionUID = 1L;
	
	@Override
	public double calculate(String s, String t) {
        int s_len = s.length();
        int t_len = t.length();

        if (s_len == 0 && t_len == 0) return 1;

        int match_distance = Integer.max(s_len, t_len) / 2 - 1;

        boolean[] s_matches = new boolean[s_len];
        boolean[] t_matches = new boolean[t_len];

        int matches = 0;
        int transpositions = 0;

        for (int i = 0; i < s_len; i++) {
            int start = Integer.max(0, i-match_distance);
            int end = Integer.min(i+match_distance+1, t_len);

            for (int j = start; j < end; j++) {
                if (t_matches[j]) continue;
                if (s.charAt(i) != t.charAt(j)) continue;
                s_matches[i] = true;
                t_matches[j] = true;
                matches++;
                break;
            }
        }

        if (matches == 0) return 0;

        int k = 0;
        for (int i = 0; i < s_len; i++) {
            if (!s_matches[i]) continue;
            while (!t_matches[k]) k++;
            if (s.charAt(i) != t.charAt(k)) transpositions++;
            k++;
        }
        
        double jaro = (((double)matches / s_len) +
                ((double)matches / t_len) +
                (((double)matches - transpositions/2.0) / matches)) / 3.0;

        if (jaro < 0.7) {
            // Jaro-Winkler parameters
            double p = 0.1; // Scaling factor
            int l = 5; // Length of common prefix

            // Calculate Jaro-Winkler similarity
            double jaroWinkler = jaro + l * p * (1 - jaro);
            
            return jaroWinkler;
        } else {
            // Return Jaro similarity if it's greater than or equal to 0.7
            return jaro;
        }
        
        
        
        
        
    }

	
	


}
