namespace test;

public class C
{
    private class Pair
    {
        public int Index { get; set; }
        public int Color { get; set; }

        public Pair(int index, int color)
        {
            Index = index;
            Color = color;
        }
    }

    private static void Task()
    {
        using var input = new StreamReader(Console.OpenStandardInput());
        using var output = new StreamWriter(Console.OpenStandardOutput());

        var collection = input.ReadLine().Split(' ').Select(it => int.Parse(it)).ToArray();
        var n = collection[0];
        var m = collection[1];
        var t = Int32.Parse(input.ReadLine());
        var arrM = new Pair[m];
        var arrN = new Pair[n];
        int x, y, c;
        for (int i = 0; i < n; ++i)
        {
            arrN[i] = new Pair(-1, 0);
        }

        for (int i = 0; i < m; ++i)
        {
            arrM[i] = new Pair(-1, 0);
        }

        for (var i = 0; i < t; ++i)
        {
            collection = input.ReadLine().Split(' ').Select(it => int.Parse(it)).ToArray();
            x = collection[0] - 1;
            y = collection[1] - 1;
            c = collection[2];
            arrN[x].Color = c;
            arrN[x].Index = i;
            arrM[y].Color = c;
            arrM[y].Index = i;
        }

        for (var i = 0; i < n; ++i)
        {
            for (var j = 0; j < m; ++j)
            {
                output.Write(arrM[j].Index > arrN[i].Index ? arrM[j].Color : arrN[i].Color);

                // if (i != m - 1)
                // {
                    output.Write(' ');
                // }
            }

            output.WriteLine();
        }
    }
}