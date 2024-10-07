import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatternMatcher {

    public static void main(String[] args) {
        String s1 = "abc";
        String p1 = "a*b*c*";
        match(s1, p1);  // True

        String s2 = "abbbbbbbbc";
        String p2 = ".*.";
        match(s2, p2);  // True
    }

    public static void match(String s, String p) {
        if (p.charAt(0) == '*') {
            System.out.println(false);
            return;
        }

        // Reducción de asteriscos consecutivos
        StringBuilder pBuilder = new StringBuilder(p);
        int i = 0;
        while (i < pBuilder.length() - 1) {
            if (pBuilder.charAt(i) == '' && pBuilder.charAt(i + 1) == '') {
                pBuilder.deleteCharAt(i + 1);
            } else {
                i++;
            }
        }
        p = pBuilder.toString();

        int sIndex = 0;
        int pIndex = 0;
        int count = 0;
        List<List<String>> aux1 = new ArrayList<>();
        List<List<String>> aux2 = new ArrayList<>();
        aux1.add(new ArrayList<>()); // matriz auxiliar inicial vacía

        while (sIndex < s.length() || pIndex < p.length()) {
            if (pIndex < p.length() && sIndex < s.length() && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.')) {
                List<List<String>> sublistsToRemove = new ArrayList<>();
                for (List<String> sublist : aux1) {
                    if (p.charAt(pIndex) == '.') {
                        if (sublist.size() < s.length()) {
                            sublist.add(String.valueOf(s.charAt(sublist.size())));
                        }
                    } else {
                        sublist.add(String.valueOf(s.charAt(sIndex)));
                    }

                    if (!String.join("", sublist).equals(s.substring(0, sublist.size()))) {
                        sublistsToRemove.add(sublist);
                    }
                }

                aux1.removeAll(sublistsToRemove);
                pIndex++;
                sIndex++;
                continue;
            }

            if (pIndex < p.length() && p.charAt(pIndex) == '*') {
                if (sIndex >= s.length()) {
                    sIndex--;
                }

                if (pIndex > 0 && p.charAt(pIndex - 1) == '.') {
                    aux2 = deepCopyList(aux1);
                    for (List<String> sublist : aux2) {
                        sublist.add(String.valueOf(s.charAt(sIndex)));
                        while (String.join("", sublist).equals(s.substring(0, sublist.size()))) {
                            aux1.add(new ArrayList<>(sublist));
                            sublist.add(String.valueOf(s.charAt(sIndex)));
                            if (sublist.size() > s.length()) {
                                break;
                            }
                        }
                    }
                } else {
                    aux2 = deepCopyList(aux1);
                    for (List<String> sublist : aux2) {
                        while (String.join("", sublist).equals(s.substring(0, sublist.size()))) {
                            aux1.add(new ArrayList<>(sublist));
                            sublist.add(sublist.get(sublist.size() - 1));
                            if (sublist.size() > s.length()) {
                                break;
                            }
                        }
                    }
                }
                pIndex++;
            } else {
                if (pIndex >= p.length()) {
                    break;
                }

                for (List<String> sublist : aux1) {
                    sublist.add(String.valueOf(p.charAt(pIndex)));
                }

                for (List<String> sublist : aux1) {
                    if (String.join("", sublist).equals(s) && pIndex == p.length() - 1) {
                        System.out.println(true);
                        return;
                    } else if (!String.join("", sublist).equals(s) && pIndex == p.length() - 1) {
                        count++;
                    }
                }

                if (count == aux1.size()) {
                    System.out.println(false);
                    return;
                } else {
                    count = 0;
                }

                if (aux1.isEmpty()) {
                    break;
                }

                pIndex++;
                sIndex++;
            }
        }

        for (List<String> sublist : aux1) {
            if (String.join("", sublist).equals(s)) {
                System.out.println(true);
                return;
            }
        }

        System.out.println(false);
    }

    // Función para hacer una copia profunda de una lista de listas
    private static List<List<String>> deepCopyList(List<List<String>> original) {
        return original.stream()
                .map(sublist -> new ArrayList<>(sublist))
                .collect(Collectors.toList());
    }
}