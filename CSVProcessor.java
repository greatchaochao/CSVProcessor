import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVProcessor {
    public static void updateRowValues(String csvFileNanme, String column, String oldValue, String newValue){
        File csvFile = new File(csvFileNanme);

        if (csvFile.isFile()) {
            boolean isUpdated = false;
            List<List<String>> outputRows = new ArrayList<>();

            try (BufferedReader csvReader = new BufferedReader(new FileReader(csvFileNanme))) {
                String row;
                int columnIndex = -1;
                int i = 0;

                // Read the csv file and replace value row by row
                while ((row = csvReader.readLine()) != null) {
                    List<String> data = Arrays.asList(row.trim().split("\\s*,\\s*"));

                    // Check if column is in the header and get the index of the column that we are searching for
                    if (i == 0) {
                        columnIndex = data.indexOf(column);
                        if (columnIndex == -1) {
                            System.out.printf("Column name '%s' not found in header, aborting... \n", column);
                            return;
                        }
                        ++i;
                        outputRows.add(data);
                        continue;
                    }

                    // Update oldValue with newValue if found
                    if (data.get(columnIndex).contains(oldValue)) {
                        String updatedValue = data.get(columnIndex).replaceAll(oldValue, newValue);
                        data.set(columnIndex, updatedValue);
                        isUpdated = true;
                        System.out.printf("'%s' found in column '%s', updated with '%s' \n", oldValue, column, newValue);
                    }

                    outputRows.add(data);
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }

            // Only update csv if it's updated
            if (isUpdated) {
                try (FileWriter csvWriter = new FileWriter(csvFileNanme)) {
                    for (List<String> rowData : outputRows) {
                        csvWriter.append(String.join(",", rowData));
                        csvWriter.append("\n");
                    }
                    csvWriter.flush();
                    System.out.printf("File '%s' updated \n", csvFileNanme);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                System.out.printf("No value '%s' found in column '%s' \n", oldValue, column);
            }
        }
        else {
            System.out.printf("File '%s' not found, aborting... \n", csvFileNanme);
        }
    }

    public static void main(String[] args) {
        if (args.length == 4) {
            updateRowValues(args[0], args[1], args[2], args[3]);
        }
        else {
            throw new IllegalArgumentException("Invalid number of arguments, expected: 4, actual: " + args.length + "\n"
                + "Usage example: java CSVProcessor \"test.csv\" origin Londom London");
        }
    }
}
