
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetinf2015;

/**
 *
 * @author Aristide
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

public class ProjetInf2015 {

    /**
     * @param json
     * @return
     */
   public static boolean validerNumeroClient(String numero) {
        boolean reponse = false;
        if (numero.length() == 6) {
            for (int i = 0; i < numero.length(); i++) {
                if (numero.charAt(i) >= '0' && numero.charAt(i) <= '9') {
                    reponse = true;
                }
            }
        }
        return reponse;
    }
   
    public static boolean validerContrat(JSONObject json) {
        boolean reponse = false;
        if (json.getString("contrat").equals("B") || json.getString("contrat").equals("A") || json.getString("contrat").equals("C") || json.getString("contrat").equals("D")) {
            reponse = true;
        }
        return reponse;
    }

    public static boolean validerMois(JSONObject json, JSONArray array) {
        boolean reponse = false;
        int compteur = 0;
        String mois = json.getString("mois").substring(5, 7);
        JSONObject obj = array.getJSONObject(compteur);
        String date = obj.getString("date").substring(5, 7);
        while (mois.equals(date)) {
            if (mois.equals(date)) {
                compteur++;
                reponse = true;
            }
            
        }
        return reponse;
    }
    
    public static double formaterMontant(String montant) {
        String montantFormate = "";
        for (int i = 0; i < montant.length(); i++) {
            char carac = montant.charAt(i);
            if (carac != '$') {
                montantFormate = montantFormate + carac;

            }
        }
        return Double.parseDouble(montantFormate);
    }

    public static double calculerRemboursementContratA(JSONObject obj) {
        int tauxRemise;
        double remboursement = 0.00;
        double montant = formaterMontant(obj.getString("montant"));
        if (obj.getInt("soin") == 0 || obj.getInt("soin") == 100 || obj.getInt("soin") == 200 || obj.getInt("soin") == 500) {
            tauxRemise = 25;
            remboursement = montant * tauxRemise / 100;
        } else if (obj.getInt("soin") == 600) {
            tauxRemise = 40;
            remboursement = montant * tauxRemise / 100;
        }
        return remboursement;
    }

    public static double calculerRemboursementContratB(JSONObject json) {
        double remboursement = 0.00;
        double montant = formaterMontant(json.getString("montant"));
        int tauxRemise = 0;
        if (json.getInt("soin") == 0) {
            tauxRemise = 50;
            if (montant <= 40.00) {
                remboursement = montant * tauxRemise / 100;
            } else {
                remboursement = 40.00;
            }
        } else if (json.getInt("soin") == 100) {
            tauxRemise = 50;
            if (montant <= 50.00) {
                remboursement = montant * tauxRemise / 100;
            } else {
                remboursement = 50.00;
            }
        } else if (json.getInt("soin") == 200) {
            tauxRemise = 100;
            if (montant <= 70.00) {
                remboursement = montant * tauxRemise / 100;
            } else {
                remboursement = 70.00;
            }
        } else if (json.getInt("soin") >= 300 && json.getInt("soin") <= 399) {
            tauxRemise = 50;
            remboursement = montant * tauxRemise / 100;
        } else if (json.getInt("soin") == 400) {
            remboursement = tauxRemise;
        } else if (json.getInt("soin") == 500) {
            tauxRemise = 50;
            if (montant <= 50.00) {
                remboursement = montant * tauxRemise / 100;
            } else {
                remboursement = 50.00;
            }
        } else if (json.getInt("soin") == 600) {
            remboursement = montant;
        } else if (json.getInt("soin") == 700) {
            tauxRemise = 70;
            remboursement = montant * tauxRemise / 100;
        }
        return remboursement;

    }

    public static double calculerRemboursementContratC(JSONObject json) {
        double remboursement;
        double montant = formaterMontant(json.getString("montant"));
        int tauxRemise = 90;
        remboursement = montant * tauxRemise / 100;
        return remboursement;

    }

    public static double calculerRemboursementContratD(JSONObject json) {
        double remboursement = 0.00;
        double montant = formaterMontant(json.getString("montant"));
        int tauxRemise = 100;
        if (json.getInt("soin") == 0) {
            if (montant <= 85.00) {
                remboursement = montant;
            } else {
                remboursement = 85.00;
            }
        } else if (json.getInt("soin") == 100) {
            if (montant <= 75.00) {
                remboursement = montant;
            } else {
                remboursement = 75.00;
            }
        } else if (json.getInt("soin") == 200) {

            if (montant <= 100.00) {
                remboursement = montant;
            } else {
                remboursement = 100.00;
            }
        } else if (json.getInt("soin") >= 300 && json.getInt("soin") <= 399) {
            remboursement = montant;
        } else if (json.getInt("soin") == 400) {
            if (montant <= 65.00) {
                remboursement = montant;
            } else {
                remboursement = 65.00;
            }
        } else if (json.getInt("soin") == 500) {
            if (montant <= 75.00) {
                remboursement = montant;
            } else {
                remboursement = 75.00;
            }
        } else if (json.getInt("soin") == 600) {
            if (montant <= 100.00) {
                remboursement = montant;
            } else {
                remboursement = 100.00;
            }
        } else if (json.getInt("soin") == 700) {
            if (montant <= 90.00) {
                remboursement = montant;
            } else {
                remboursement = 90.00;
            }
        }
        return remboursement;
    }

    public static void ecrire(String nomFichier, JSONObject json) {
        FileWriter writer;
        PrintWriter printer;
        String chaineLue;
        try {
            writer = new FileWriter(nomFichier); // IOException
            printer = new PrintWriter(writer);

            chaineLue = json.toString();
            printer.println(chaineLue);

            // Ne pas oublier de fermer le fichier
            printer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {

        FileReader reader = new FileReader();
        String txt = reader.loadFileIntoString(args[0], "utf-8");
        JSONObject objet = JSONObject.fromObject(txt);
        JSONArray array = objet.getJSONArray("reclamations");

        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormatSymbols dcmlFS = new DecimalFormatSymbols();
        dcmlFS.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dcmlFS);

        if (validerNumeroClient(objet.getString("client")) && validerContrat(objet)) {

            JSONObject outputObj = new JSONObject();
            JSONObject newObj = new JSONObject();
            JSONArray remboursements = new JSONArray();
            double montant = 0.00;

            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = array.getJSONObject(i);
                switch (objet.getString("contrat")) {
                    case "A":
                        montant = calculerRemboursementContratA(obj);
                        break;
                    case "B":
                        montant = calculerRemboursementContratB(obj);
                        break;
                    case "C":
                        montant = calculerRemboursementContratC(obj);
                        break;
                    case "D":
                        montant = calculerRemboursementContratD(obj);
                        break;
                }

                newObj.put("soin", obj.getInt("soin"));
                newObj.put("date", obj.getString("date"));
                newObj.put("montant", df.format(montant) + "$");
                remboursements.add(newObj);
            }

            outputObj.accumulate("client", objet.getString("client"));
            outputObj.accumulate("mois", objet.getString("mois"));
            outputObj.accumulate("remboursements", remboursements);

            System.out.println(outputObj);
            ecrire(args[1], outputObj);

        } else {
            JSONObject errorOutput = new JSONObject();
            errorOutput.put("message", "Données invalides");
            System.out.println(errorOutput);
            ecrire(args[1], errorOutput);
        }
        
      


    }
}
