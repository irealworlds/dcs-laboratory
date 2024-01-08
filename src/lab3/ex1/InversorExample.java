package lab3.ex1;

import java.util.EnumMap;
import java.util.Map;

public class InversorExample {
    public static TwoXTwoTable createInversor() {

        // construct tabela1 FLRS for inversor, first output

        Map<FuzzyValue, Map<FuzzyValue, FuzzyValue>> ruleTable1 = new EnumMap<>(FuzzyValue.class);
        //// red - are the x3 values
        Map<FuzzyValue, FuzzyValue> nlLine = new EnumMap<>(FuzzyValue.class);
        // NL line
        // NL, NM, ZR, PM, PL columns
        // (column->line) NL->PL, NM->PM, ZR->ZR, PM->NL, PL-ZR
        ruleTable1.put(FuzzyValue.NL, nlLine);
        nlLine.put(FuzzyValue.NL, FuzzyValue.PL);
        nlLine.put(FuzzyValue.NM, FuzzyValue.PM);
        nlLine.put(FuzzyValue.ZR, FuzzyValue.ZR);
        nlLine.put(FuzzyValue.PM, FuzzyValue.NL);
        nlLine.put(FuzzyValue.PL, FuzzyValue.ZR);

        Map<FuzzyValue, FuzzyValue> nmLine = new EnumMap<>(FuzzyValue.class);
        // NM line
        // NL, NM, ZR, PM, PL columns
        // (column->line) NL->PL, NM->NM, ZR->PL, PM->PL, PL->NM
        ruleTable1.put(FuzzyValue.NM, nmLine);
        nmLine.put(FuzzyValue.NL, FuzzyValue.PL);
        nmLine.put(FuzzyValue.NM, FuzzyValue.NM);
        nmLine.put(FuzzyValue.ZR, FuzzyValue.PL);
        nmLine.put(FuzzyValue.PM, FuzzyValue.PL);
        nmLine.put(FuzzyValue.PL, FuzzyValue.NM);

        Map<FuzzyValue, FuzzyValue> zrLine = new EnumMap<>(FuzzyValue.class);
        // ZR line
        // NL, NM, ZR, PM, PL columns
        // (column->line) NL->NL, NM->PL, ZR->ZR, PM->ZR, PL->PL
        ruleTable1.put(FuzzyValue.ZR, zrLine);
        zrLine.put(FuzzyValue.NL, FuzzyValue.NL);
        zrLine.put(FuzzyValue.NM, FuzzyValue.PL);
        zrLine.put(FuzzyValue.ZR, FuzzyValue.ZR);
        zrLine.put(FuzzyValue.PM, FuzzyValue.ZR);
        zrLine.put(FuzzyValue.PL, FuzzyValue.PL);

        Map<FuzzyValue, FuzzyValue> pmLine = new EnumMap<>(FuzzyValue.class);
        // PM line
        // NL, NM, ZR, PM, PL columns
        // (column->line) NL->ZR, NM->ZR, ZR->NM, PM->PM, PL->NL
        ruleTable1.put(FuzzyValue.PM, pmLine);
        pmLine.put(FuzzyValue.NL, FuzzyValue.ZR);
        pmLine.put(FuzzyValue.NM, FuzzyValue.ZR);
        pmLine.put(FuzzyValue.ZR, FuzzyValue.NM);
        pmLine.put(FuzzyValue.PM, FuzzyValue.PM);
        pmLine.put(FuzzyValue.PL, FuzzyValue.NL);

        Map<FuzzyValue, FuzzyValue> plLine = new EnumMap<>(FuzzyValue.class);
        // PL line
        // NL, NM, ZR, PM, PL columns
        // (columns->line) NL->PM, NM->PM, ZR->ZR, PM->NM, PL->PL
        ruleTable1.put(FuzzyValue.PL, plLine);
        plLine.put(FuzzyValue.NL, FuzzyValue.PM);
        plLine.put(FuzzyValue.NM, FuzzyValue.PM);
        plLine.put(FuzzyValue.ZR, FuzzyValue.ZR);
        plLine.put(FuzzyValue.PM, FuzzyValue.NM);
        plLine.put(FuzzyValue.PL, FuzzyValue.PL);

        // construct tabela2 FLRS for inversor, the second output

        Map<FuzzyValue, Map<FuzzyValue, FuzzyValue>> ruleTable2 = new EnumMap<>(FuzzyValue.class);

        //// blue - are the x4 values
        Map<FuzzyValue, FuzzyValue> nlLine2 = new EnumMap<>(FuzzyValue.class);
        // NL line
        // NL, NM, ZR, PM, PL columns
        // (columns->line) NL->PL, NM->NL, ZR->ZR, PM->PL, PL->PL
        ruleTable2.put(FuzzyValue.NL, nlLine2);
        nlLine2.put(FuzzyValue.NL, FuzzyValue.PL);
        nlLine2.put(FuzzyValue.NM, FuzzyValue.NL);
        nlLine2.put(FuzzyValue.ZR, FuzzyValue.ZR);
        nlLine2.put(FuzzyValue.PM, FuzzyValue.PL);
        nlLine2.put(FuzzyValue.PL, FuzzyValue.PL);

        Map<FuzzyValue, FuzzyValue> nmLine2 = new EnumMap<>(FuzzyValue.class);

        // NM line
        // NL, NM, ZR, PM, PL columns
        // (columns->line) NL->PM, NM->ZR, ZR->NM, PM->NM, PL->PL
        ruleTable2.put(FuzzyValue.NM, nmLine2);
        nmLine2.put(FuzzyValue.NL, FuzzyValue.PM);
        nmLine2.put(FuzzyValue.NM, FuzzyValue.ZR);
        nmLine2.put(FuzzyValue.ZR, FuzzyValue.NM);
        nmLine2.put(FuzzyValue.PM, FuzzyValue.NM);
        nmLine2.put(FuzzyValue.PL, FuzzyValue.PL);

        Map<FuzzyValue, FuzzyValue> zrLine2 = new EnumMap<>(FuzzyValue.class);

        // ZR line
        // NL, NM, ZR, PM, PL columns
        // (columns->line) NL->PM, NM->NM, ZR->ZR, PM->NM, PL->ZR
        ruleTable2.put(FuzzyValue.ZR, zrLine2);
        zrLine2.put(FuzzyValue.NL, FuzzyValue.PM);
        zrLine2.put(FuzzyValue.NM, FuzzyValue.NM);
        zrLine2.put(FuzzyValue.ZR, FuzzyValue.ZR);
        zrLine2.put(FuzzyValue.PM, FuzzyValue.NM);
        zrLine2.put(FuzzyValue.PL, FuzzyValue.ZR);

        Map<FuzzyValue, FuzzyValue> pmLine2 = new EnumMap<>(FuzzyValue.class);

        // PM line
        // NL, NM, ZR, PM, PL columns
        // (columns->line) NL->PL, NM->PM, ZR->PM, PM->PM, PL->NL
        ruleTable2.put(FuzzyValue.PM, pmLine2);
        pmLine2.put(FuzzyValue.NL, FuzzyValue.PL);
        pmLine2.put(FuzzyValue.NM, FuzzyValue.PM);
        pmLine2.put(FuzzyValue.ZR, FuzzyValue.PM);
        pmLine2.put(FuzzyValue.PM, FuzzyValue.PM);
        pmLine2.put(FuzzyValue.PL, FuzzyValue.NL);

        Map<FuzzyValue, FuzzyValue> plLine2 = new EnumMap<>(FuzzyValue.class);

        // PL line
        // NL, NM, ZR, PM, PL columns
        // (columns->line) NL->ZR, NM->NM, ZR->ZR, PM->ZR, PL->NM
        ruleTable2.put(FuzzyValue.PL, plLine2);
        plLine2.put(FuzzyValue.NL, FuzzyValue.ZR);
        plLine2.put(FuzzyValue.NM, FuzzyValue.NM);
        plLine2.put(FuzzyValue.ZR, FuzzyValue.ZR);
        plLine2.put(FuzzyValue.PM, FuzzyValue.ZR);
        plLine2.put(FuzzyValue.PL, FuzzyValue.NM);

        // returning FLRS table with two inputs and two outputs
        return new TwoXTwoTable(ruleTable1, ruleTable2);
    }

    public static void main(String[] args) {
        double w1 = 2;
        double w2 = 3;

        // specifying the limits for fuzzyfication, defuzzyfication
        FuzzyDefuzzy fuzDefuz = new FuzzyDefuzzy(-1.0, -0.5, 0.0, 0.5, 1.0);

        // creating FLRS table for tow inputs and two outputs
        TwoXTwoTable inversor = createInversor();

        // giving the two inputs

        double x1 = 0.3;
        double x2 = 0.7;

        // multiplying the inputs with the amplification and fuzzyfication factors
        FuzzyToken inpToken1 = fuzDefuz.fuzzyfie(x1 * w1);
        FuzzyToken inpToken2 = fuzDefuz.fuzzyfie(x2 * w2);

        // executing the FLRS table

        FuzzyToken[] out = inversor.execute(inpToken1, inpToken2);

        // displaying the defuzzyfication results

        System.out.println("x3 :: " + fuzDefuz.defuzzify(out[0]));
        System.out.println("x4 :: " + fuzDefuz.defuzzify(out[1]));

    }
}