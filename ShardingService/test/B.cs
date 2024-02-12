namespace test;

public class B
{
    private static void Task()
    {
        using var input = new StreamReader(Console.OpenStandardInput());
        using var output = new StreamWriter(Console.OpenStandardOutput());
        
        var n = Int32.Parse(input.ReadLine());
        var a = input.ReadLine().Split(' ').Select(it => int.Parse(it)).ToArray();
        var postSum = a.Sum() - a[0];
        var prevSum = a[0];
        // var ans = new string("");
        var ans = new List<char>(2 * n);
        ans.Add(char.Parse(a[0].ToString()));

        var i = 1;
        while (i < n && prevSum != postSum)
        {
            ans.Add('+');
            ans.Add(char.Parse(a[i].ToString()));
            prevSum += a[i];
            postSum -= a[i];
            ++i;
        }

        if (i == n)
        {
            output.WriteLine(-1);
        }
        else
        {
            foreach (var ch in ans)
            {
                output.Write(ch);
            }

            output.Write('=');
            output.Write(a[i]);
            ++i;
            while (i < n)
            {
                output.Write('+');
                output.Write(a[i]);
                ++i;
            }
        }
    }
}