using System;

namespace test;

public static class Program
{
    private static void Main()
    {
        Task();
    }

     private static int FindMax(IReadOnlyList<int> a, int n, int k, int max)
    {
        var right = 0;
        var left = 0;
        var count = 0;
        for (var i = 0; i < n; ++i)
        {
            left = i;
            right = n;
            while (right - left > 1)
            {
                var mid = left + (right - left) / 2;
                if (i > 0)
                {
                    count = a[mid] - a[i - 1];
                }
                else
                {
                    count = a[mid];
                }

                if (count > k)
                {
                    right = mid;
                }
                else
                {
                    left = mid;
                }
            }

            
            
            
            
            // if (i > 0 )
            // {
            //     count = a[left] - a[i - 1];
            // }
            // else
            // {
            //     count = a[left];
            // }
            // if (left + 1 < n && a[left + 1]- a[i - 1] > k)
            // {
                if (left - i + 1 > max)
                {
                    max = left - i + 1;
                }
            // }
            // else 
            // {
            //     if (right - i + 1 > max)
            //     {
            //         max = right - i + 1;
            //     }
            // }
        }

        return max;
    }

    private static void Task()
    {
        using var input = new StreamReader(Console.OpenStandardInput());
        using var output = new StreamWriter(Console.OpenStandardOutput());
        var collection = input.ReadLine().Split(' ').Select(it => int.Parse(it)).ToArray();
        var n = collection[0];
        var k = collection[1];
        var str = input.ReadLine();
        var r = new int[n];
        var g = new int[n];
        var b = new int[n];
        switch (str[0])
        {
            case 'R':
                r[0] = 0;
                g[0] = 1;
                b[0] = 1;
                break;
            case 'G':
                r[0] = 1;
                g[0] = 0;
                b[0] = 1;
                break;
            case 'B':
                r[0] = 1;
                g[0] = 1;
                b[0] = 0;
                break;
        }

        for (var i = 1; i < str?.Length; ++i)
        {
            switch (str[i])
            {
                case 'R':
                    r[i] = r[i - 1];
                    g[i] = g[i - 1] + 1;
                    b[i] = b[i - 1] + 1;
                    break;
                case 'G':
                    r[i] = r[i - 1] + 1;
                    g[i] = g[i - 1];
                    b[i] = b[i - 1] + 1;
                    break;
                case 'B':
                    r[i] = r[i - 1] + 1;
                    g[i] = g[i - 1] + 1;
                    b[i] = b[i - 1];
                    break;
            }
        }

        var max = 0;
        max = FindMax(r, n, k, max);
        max = FindMax(g, n, k, max);
        max = FindMax(b, n, k, max);
        output.WriteLine(max);
    }
}