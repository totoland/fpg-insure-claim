/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.dbmrgdao;

/**
 *
 * @author totoland
 */
public class App {

    public static void main(String args[]) {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT     REPORT_?.REPORT_ID, REPORT_?.REPORT_CODE, REPORT_?.REPORT_DESC, REPORT_?.CREATED_DATE, REPORT_?.CREATED_USER, \n"
                + "                      REPORT_?.UPDATED_DATE, REPORT_?.UPDATED_USER, REPORT_?.REMARK, REPORT_?.APPROVED_USER, REPORT_?.FLOW_STATUS_ID, \n"
                + "                      ECT_USER.USERNAME AS CREATED_USER_NAME, ECT_FLOW_STATUS.FLOW_STATUS_NAME, ECT_USER_1.USERNAME AS UPDATED_USER_NAME, \n"
                + "                      ECT_USER_2.USERNAME AS APPROVED_USER_NAME, REPORT_?.APPROVED_DATE, ECT_USER_3.USERNAME AS REJECTED_USER_NAME, \n"
                + "                      REPORT_?.REJECTED_USER, REPORT_?.REJECTED_DATE, ECT_USER.FNAME + '  ' + ECT_USER.LNAME AS CREATED_USER_FULL_NAME, \n"
                + "                      ECT_USER_1.FNAME + '  ' + ECT_USER_1.LNAME AS UPDATED_USER_FULL_NAME, \n"
                + "                      ECT_USER_2.FNAME + '  ' + ECT_USER_2.LNAME AS APPROVED_USER_FULL_NAME, \n"
                + "                      ECT_USER_3.FNAME + '  ' + ECT_USER_3.LNAME AS REJECTED_USER_FULL_NAME, ISNULL(REPORT_?.REPORT_STATUS, 100) AS REPORT_STATUS, \n"
                + "                      ECT_USER.USER_GROUP_ID, ECT_USER.USER_GROUP_LVL, REPORT_?.REPORT_MONTH, REPORT_?.REPORT_YEAR, \n"
                + "                      CASE WHEN ISNULL(REPORT_?.CREATED_DATE, '') > ISNULL(REPORT_?.APPROVED_DATE, '') AND ISNULL(REPORT_?.CREATED_DATE, '') \n"
                + "                      > ISNULL(REPORT_?.REJECTED_DATE, '') THEN REPORT_?.CREATED_DATE WHEN ISNULL(REPORT_?.APPROVED_DATE, '') \n"
                + "                      > ISNULL(REPORT_?.CREATED_DATE, '') AND ISNULL(REPORT_?.APPROVED_DATE, '') > ISNULL(REPORT_?.REJECTED_DATE, '') \n"
                + "                      THEN REPORT_?.APPROVED_DATE WHEN ISNULL(REPORT_?.REJECTED_DATE, '') > ISNULL(REPORT_?.CREATED_DATE, '') AND \n"
                + "                      ISNULL(REPORT_?.REJECTED_DATE, '') > ISNULL(REPORT_?.APPROVED_DATE, '') THEN REPORT_?.REJECTED_DATE END AS ACTION_DATE, \n"
                + "                      ECT_USER_GROUP.USER_GROUP_NAME\n"
                + "FROM         REPORT_? INNER JOIN\n"
                + "                      ECT_USER ON REPORT_?.CREATED_USER = ECT_USER.USER_ID INNER JOIN\n"
                + "                      ECT_FLOW_STATUS ON REPORT_?.FLOW_STATUS_ID = ECT_FLOW_STATUS.FLOW_STATUS_ID INNER JOIN\n"
                + "                      ECT_USER_GROUP ON ECT_USER.USER_GROUP_ID = ECT_USER_GROUP.USER_GROUP_ID LEFT OUTER JOIN\n"
                + "                      ECT_USER AS ECT_USER_3 ON REPORT_?.REJECTED_USER = ECT_USER_3.USER_ID LEFT OUTER JOIN\n"
                + "                      ECT_USER AS ECT_USER_2 ON REPORT_?.APPROVED_USER = ECT_USER_2.USER_ID LEFT OUTER JOIN\n"
                + "                      ECT_USER AS ECT_USER_1 ON REPORT_?.UPDATED_USER = ECT_USER_1.USER_ID"
                + " UNION ALL\n");

        for (int i = 1; i < 10; i++) {
            System.out.println(sql.toString().replaceAll("\\?", "00" + i));
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println(sql.toString().replaceAll("\\?", "0" + i));
        }

        sql = new StringBuilder();

        sql.append("ALTER TABLE REPORT_? ADD REPORT_MONTH [int] NULL;\n"
                + "ALTER TABLE REPORT_? ADD REPORT_YEAR [int] NULL;\n"
                + "\n"
                + "EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'รายงานประจำเดือน' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'REPORT_?', @level2type=N'COLUMN',@level2name=N'REPORT_MONTH'\n"
                + "GO\n"
                + "\n"
                + "EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'รายงานของปี' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'REPORT_?', @level2type=N'COLUMN',@level2name=N'REPORT_YEAR'\n"
                + "GO\n"
                + "\n"
                + "ALTER TABLE REPORT_?_HIS ADD REPORT_MONTH [int] NULL;\n"
                + "ALTER TABLE REPORT_?_HIS ADD REPORT_YEAR [int] NULL;\n"
                + "\n"
                + "EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'รายงานประจำเดือน' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'REPORT_?_HIS', @level2type=N'COLUMN',@level2name=N'REPORT_MONTH'\n"
                + "GO\n"
                + "\n"
                + "EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'รายงานของปี' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'REPORT_?_HIS', @level2type=N'COLUMN',@level2name=N'REPORT_YEAR'\n"
                + "GO\n"
                + "\n"
                + "UPDATE REPORT_? SET REPORT_MONTH = right('0'+ rtrim(DATEPART(month,CREATED_DATE)), 2),REPORT_YEAR = DATEPART(year,CREATED_DATE)+543\n"
                + "UPDATE REPORT_?_HIS SET REPORT_MONTH = right('0'+ rtrim(DATEPART(month,CREATED_DATE)), 2),REPORT_YEAR = DATEPART(year,CREATED_DATE)+543");

        for (int i = 1; i < 10; i++) {
            System.out.println(sql.toString().replaceAll("\\?", "00"+i));
        }
        
        for (int i = 10; i <= 23; i++) {
            System.out.println(sql.toString().replaceAll("\\?", "0"+i));
        }
        
        for (int i = 1; i < 10; i++) {
            System.out.println("UPDATE [REPORT_00"+i+"] SET [REPORT_YEAR] = 2557");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("UPDATE [REPORT_0"+i+"] SET [REPORT_YEAR] = 2557");
        }

        for (int i = 1; i < 10; i++) {
            System.out.println("UPDATE [REPORT_00"+i+"_HIS] SET [REPORT_YEAR] = 2557");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("UPDATE [REPORT_0"+i+"_HIS] SET [REPORT_YEAR] = 2557");
        }
        
        for (int i = 1; i < 10; i++) {
            System.out.println("DELETE [REPORT_00"+i+"_DETAIL]");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("DELETE [REPORT_0"+i+"_DETAIL]");
        }
        
        for (int i = 1; i < 10; i++) {
            System.out.println("DELETE [REPORT_00"+i+"_DETAIL_HIS]");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("DELETE [REPORT_0"+i+"_DETAIL_HIS]");
        }
        
        for (int i = 1; i < 10; i++) {
            System.out.println("DELETE [REPORT_00"+i+"]");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("DELETE [REPORT_0"+i+"]");
        }
        
        for (int i = 1; i < 10; i++) {
            System.out.println("DELETE [REPORT_00"+i+"_HIS]");
        }

        for (int i = 10; i <= 23; i++) {
            System.out.println("DELETE [REPORT_0"+i+"_HIS]");
        }
    }
}
