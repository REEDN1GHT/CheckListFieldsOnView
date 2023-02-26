package Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.example.GUI.*;
import static Page.ButtonEvent.*;



public class SQL extends BD {
    public static List<String> ListFieldsLeft = new ArrayList<>();
    public static List<String> ListFieldsRight = new ArrayList<>();
    public static List<String> ListFilters = new ArrayList<>();
    public static String FormID;
    public static String RequestID;

    public void SqlForm() {
        try {
            Statement statementForm = getConnectionBudget23UKT().createStatement();
            String SqlInnerText = "select Views_Forms.ID as 'Ид формы', Views_Forms.Name  as 'Название формы просмотра', Views_Queries.ID as 'Ид запроса' from Views_Forms with(nolock)\n" +
                    "left join Views_Queries on Views_Forms.QueryID = Views_Queries.ID where Views_Forms.Name =\'" + staticNameForm + "\'";
            ResultSet ResultSql = statementForm.executeQuery(SqlInnerText);
            while (ResultSql.next()) {
                FormID = ResultSql.getString("Ид формы");
                RequestID = ResultSql.getString("Ид запроса");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> SqlFormFields() {
    try {
        Statement statementFormView = getConnectionBudget23KF().createStatement();
        String SQLField ="SELECT distinct Position, DefPos, Caption = (SELECT CASE WHEN Caption = 'Год 2013' THEN 'Год 2023' \n" +
                "                                       when Caption = 'Год 2014' THEN 'Год 2024'\n" +
                "                                       when Caption = 'Год 2015' THEN 'Год 2025'" +
                "                                       when Caption = 'Оценка 2012' THEN 'Оценка 2022'\n" +
                "                                       when Caption = 'Оценка 2012 без КВ' THEN 'Оценка 2022 без КВ'\n" +
                "                                       when Caption = 'Оценка 2013' THEN 'Оценка 2023'\n" +
                "                                       when Caption = 'Оценка 2013 без КВ' THEN 'Оценка 2023 без КВ'\n" +
                "                                       when Caption = 'Оценка 2014' THEN 'Оценка 2024'\n" +
                "                                       when Caption = 'Оценка 2014 без КВ' THEN 'Оценка 2024 без КВ'\n" +
                "                                       when Caption = 'План 2012' THEN 'План 2022'\n" +
                "                                       when Caption = 'План 2012 без КВ' THEN 'План 2022 без КВ'\n" +
                "                                       when Caption = 'План 2013' THEN 'План 2023'\n" +
                "                                       when Caption = 'План 2013 без КВ' THEN 'План 2023 без КВ'\n" +
                "                                       when Caption = 'План 2014' THEN 'План 2024'\n" +
                "                                       when Caption = 'План 2014 без КВ' THEN 'План 2024 без КВ'\n" +
                "                                       when Caption = 'План 2015' THEN 'План 2025'\n" +
                "                                       when Caption = 'План 2015 без КВ' THEN 'План 2025 без КВ'\n" +
                "                                       when Caption = 'План 2016 без КВ' THEN 'План 2026 без КВ'\n" +
                "                                       when Caption = 'Факт 2012 без КВ' THEN 'Факт 2022 без КВ'\n" +
                "                                       when Caption = 'Факт 2012' THEN 'Факт 2022'" +
                "                                       when Caption = 'Прогноз 2016' THEN 'Прогноз 2026'" +
                " ELSE \"caption\" end), Visible, Total, Parameter, Views_QueriesSelect.Filter FROM Views_FormsFields\n" +
                "join Views_Fields on Views_Fields.ID = Views_FormsFields.FieldID\n" +
                "join Views_QueriesSelect on Views_QueriesSelect.FieldID = Views_FormsFields.FieldID and Views_QueriesSelect.QueryID = Views_FormsFields.QueryID\n" +
                "where FormID =\'" + FormID + "\'" +
                "and visible ='true' \n" +
                "and Views_FormsFields.QueryID =" + RequestID;
        ResultSet result = statementFormView.executeQuery(SQLField);
        while (result.next()) {
            String Fields = result.getString("Caption");
            ListFieldsLeft.add(String.join(" ", Fields).trim().replaceAll("  ", " "));
            Collections.sort(ListFieldsLeft);
        }
    } catch (SQLException | ClassNotFoundException throwables) {
        throwables.printStackTrace();
    }
        return ListFieldsLeft;
    }
    public List<String> SqlFormFieldsRight() {
        try {
            Statement statementFormView = getConnectionBudget23KF().createStatement();
            String SQLField ="SELECT distinct Position, DefPos, Caption, Visible, Total, Parameter, Views_QueriesSelect.Filter FROM Views_FormsFields\n" +
                    "join Views_Fields on Views_Fields.ID = Views_FormsFields.FieldID\n" +
                    "join Views_QueriesSelect on Views_QueriesSelect.FieldID = Views_FormsFields.FieldID and Views_QueriesSelect.QueryID = Views_FormsFields.QueryID\n" +
                    "where FormID =\'" + FormID + "\'" +
                    "and position is not null\n" +
                    "and Views_FormsFields.QueryID =" + RequestID;
            ResultSet result = statementFormView.executeQuery(SQLField);
            while (result.next()) {
                String Fields = result.getString("Caption");
                ListFieldsRight.add(String.join(" ", Fields).trim().replaceAll("  ", " "));
                Collections.sort(ListFieldsRight);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return ListFieldsRight;
    }

    public List<String> SqlFormFilters(){
        try{
            Statement statementFormFilters = getConnectionBudget23UKT().createStatement();
            String SqlFilters = "SELECT distinct Position, DefPos, Caption = (SELECT CASE WHEN Caption = 'Год 2013' THEN 'Год 2023' \n" +
                    "                                       when Caption = 'Год 2014' THEN 'Год 2024'\n" +
                    "                                       when Caption = 'Год 2015' THEN 'Год 2025'" +
                    "                                       when Caption = 'Оценка 2012' THEN 'Оценка 2022'\n" +
                    "                                       when Caption = 'Оценка 2012 без КВ' THEN 'Оценка 2022 без КВ'\n" +
                    "                                       when Caption = 'Оценка 2013' THEN 'Оценка 2023'\n" +
                    "                                       when Caption = 'Оценка 2013 без КВ' THEN 'Оценка 2023 без КВ'\n" +
                    "                                       when Caption = 'Оценка 2014' THEN 'Оценка 2024'\n" +
                    "                                       when Caption = 'Оценка 2014 без КВ' THEN 'Оценка 2024 без КВ'\n" +
                    "                                       when Caption = 'План 2012' THEN 'План 2022'\n" +
                    "                                       when Caption = 'План 2012 без КВ' THEN 'План 2022 без КВ'\n" +
                    "                                       when Caption = 'План 2013' THEN 'План 2023'\n" +
                    "                                       when Caption = 'План 2013 без КВ' THEN 'План 2023 без КВ'\n" +
                    "                                       when Caption = 'План 2014' THEN 'План 2024'\n" +
                    "                                       when Caption = 'План 2014 без КВ' THEN 'План 2024 без КВ'\n" +
                    "                                       when Caption = 'План 2015' THEN 'План 2025'\n" +
                    "                                       when Caption = 'План 2015 без КВ' THEN 'План 2025 без КВ'\n" +
                    "                                       when Caption = 'План 2016 без КВ' THEN 'План 2026 без КВ'\n" +
                    "                                       when Caption = 'Факт 2012 без КВ' THEN 'Факт 2022 без КВ'\n" +
                    "                                       when Caption = 'Факт 2012' THEN 'Факт 2022'" +
                    "                                       when Caption = 'Прогноз 2016' THEN 'Прогноз 2026'" +
                    " ELSE \"caption\" end), Visible, Total, Parameter, Views_QueriesSelect.Filter FROM Views_FormsFields\n" +
                    "join Views_Fields on Views_Fields.ID = Views_FormsFields.FieldID\n" +
                    "join Views_QueriesSelect on Views_QueriesSelect.FieldID = Views_FormsFields.FieldID and Views_QueriesSelect.QueryID = Views_FormsFields.QueryID\n" +
                    "where FormID =\'" + FormID + "\'" +
                    "and visible ='true' \n" +
                    "and Views_QueriesSelect.Filter='true' \n" +
                    "and Views_FormsFields.QueryID =" + RequestID;
            ResultSet result = statementFormFilters.executeQuery(SqlFilters);
            while (result.next()) {
                String Field = result.getString("Caption");
                ListFilters.add(String.join(" ", Field).trim().replaceAll("  ", " "));
                Collections.sort(ListFilters);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return ListFilters;
    }
}
