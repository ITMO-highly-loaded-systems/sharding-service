namespace ConsoleApp;

public class A
{
    private static void Task()
    {
        using var input = new StreamReader(Console.OpenStandardInput());
        using var output = new StreamWriter(Console.OpenStandardOutput());

        var collection = input.ReadLine().Split(' ').Select(it => int.Parse(it)).ToArray();

        var a = collection[0];
        var b = collection[1];
        var ans = 0;
        if (a > b)
        {
            ans += 3 * b + 1;
        }
        else if (b > a)
        {
            ans += 3 * a + 2;
        }
        else
        {
            ans += 3 * a;
        }

        output.WriteLine(ans);
    }
}