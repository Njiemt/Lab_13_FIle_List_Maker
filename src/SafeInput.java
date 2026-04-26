import java.util.Scanner;

public class SafeInput
{

    public static String getNonZeroLenString(Scanner input, String prompt)
    {
        String value = "";
        int done = 0;

        do
        {
            IO.println(prompt);
            value = input.nextLine();

            if(value.length() > 0)
            {
                done = 1;
            }
            else
            {
                IO.println("Input cannot be blank. Try again.");
            }

        }while(done == 0);

        return value;
    }

    public static int getInt(Scanner input, String prompt)
    {
        int value = 0;
        int done = 0;

        do
        {
            IO.println(prompt);

            if(input.hasNextInt())
            {
                value = input.nextInt();
                input.nextLine();
                done = 1;
            }
            else
            {
                IO.println("Invalid integer. Try again.");
                input.nextLine();
            }

        }while(done == 0);

        return value;
    }

    public static double getDouble(Scanner input, String prompt)
    {
        double value = 0;
        int done = 0;

        do
        {
            IO.println(prompt);

            if(input.hasNextDouble())
            {
                value = input.nextDouble();
                input.nextLine();
                done = 1;
            }
            else
            {
                IO.println("Invalid double. Try again.");
                input.nextLine();
            }

        }while(done == 0);

        return value;
    }

    public static int getRangedInt(Scanner input, String prompt, int low, int high)
    {
        int value = 0;
        int done = 0;

        do
        {
            IO.println(prompt + " [" + low + "-" + high + "]");

            if(input.hasNextInt())
            {
                value = input.nextInt();
                input.nextLine();

                if(value >= low && value <= high)
                {
                    done = 1;
                }
                else
                {
                    IO.println("Number must be between " + low + " and " + high);
                }
            }
            else
            {
                IO.println("Invalid integer.");
                input.nextLine();
            }

        }while(done == 0);

        return value;
    }

    public static double getRangedDouble(Scanner input, String prompt, double low, double high)
    {
        double value = 0;
        int done = 0;

        do
        {
            IO.println(prompt + " [" + low + "-" + high + "]");

            if(input.hasNextDouble())
            {
                value = input.nextDouble();
                input.nextLine();

                if(value >= low && value <= high)
                {
                    done = 1;
                }
                else
                {
                    IO.println("Number must be between " + low + " and " + high);
                }
            }
            else
            {
                IO.println("Invalid double.");
                input.nextLine();
            }

        }while(done == 0);

        return value;
    }

    public static boolean getYNConfirm(Scanner input, String prompt)
    {
        String answer = "";
        int done = 0;

        do
        {
            IO.println(prompt + " [Y/N]");
            answer = input.nextLine();

            if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))
            {
                done = 1;
            }
            else
            {
                IO.println("Please enter Y or N.");
            }

        }while(done == 0);

        if(answer.equalsIgnoreCase("y"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static String getRegExString(Scanner input, String prompt, String regEx)
    {
        String value = "";
        int done = 0;

        do
        {
            IO.println(prompt);
            value = input.nextLine();

            if(value.matches(regEx))
            {
                done = 1;
            }
            else
            {
                IO.println("Invalid format. Try again.");
            }

        }while(done == 0);

        return value;
    }

    public static void prettyHeader(String msg)
    {
        int width = 60;
        int msgLength = msg.length();
        int spaces = (width - msgLength - 6) / 2;

        for(int i = 0; i < width; i++)
        {
            IO.print("*");
        }
        IO.println("");

        IO.print("***");

        for(int i = 0; i < spaces; i++)
        {
            IO.print(" ");
        }

        IO.print(msg);

        for(int i = 0; i < width - 6 - spaces - msgLength; i++)
        {
            IO.print(" ");
        }

        IO.println("***");

        for(int i = 0; i < width; i++)
        {
            IO.print("*");
        }

        IO.println("");
    }
}