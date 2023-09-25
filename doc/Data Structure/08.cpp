#include <iostream>
#include <vector>
#include <queue>

using namespace std;
class Graph
{
private:
    int numVertices;
    vector<vector<pair<int, int>>> adjacencyList;

public:
    Graph(int numVertices)
    {
        this->numVertices = numVertices;
        adjacencyList.resize(numVertices);
    }
    void addEdge(int src, int dest, int weight)
    {
        adjacencyList[src].push_back(make_pair(dest, weight));
    }
    void printGraph()
    {
        for (int i = 0; i < numVertices; ++i)
        {
            cout << "顶点 " << i << " 的相邻顶点: ";
            for (int j = 0; j < adjacencyList[i].size(); j++)
            {
                int dest = adjacencyList[i][j].first;
                int weight = adjacencyList[i][j].second;
                cout << "(" << dest << ", " << weight << ") ";
            }
            cout << endl;
        }
    }
    vector<int> dijkstra(int startPosition)
    {
        vector<int> dist(numVertices, 10000);
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        dist[startPosition] = 0;
        pq.push(make_pair(0, startPosition));

        while (!pq.empty())
        {
            int u = pq.top().second;
            pq.pop();

            for (int i = 0; i < adjacencyList[u].size(); i++)
            {
                int v = adjacencyList[u][i].first;
                int weight = adjacencyList[u][i].second;

                if (dist[u] + weight < dist[v])
                {
                    dist[v] = dist[u] + weight;
                    pq.push(make_pair(dist[v], v));
                }
            }
        }
        return dist;
    }
    vector<vector<int>> floyd()
    {
        int numVertices = adjacencyList.size();

        // 初始化距离矩阵
        vector<vector<int>> dist(numVertices, vector<int>(numVertices, INT_MAX));

        // 将邻接列表中的边权重填入距离矩阵
        for (int i = 0; i < numVertices; ++i)
        {
            for (const auto& edge : adjacencyList[i])
            {
                int j = edge.first;
                int weight = edge.second;
                dist[i][j] = weight;
            }
        }

        // Floyd算法主循环
        for (int k = 0; k < numVertices; ++k)
        {
            for (int i = 0; i < numVertices; ++i)
            {
                for (int j = 0; j < numVertices; ++j)
                {
                    if (dist[i][k] != INT_MAX && dist[k][j] != INT_MAX && dist[i][k] + dist[k][j] < dist[i][j])
                    {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

};

int main()
{
    int numVertices;
    cin >> numVertices;
    Graph graph(numVertices);
    //这个for是输入，接收那个邻接矩阵的，不用管
    for (int i = 0; i < numVertices; i++)
    {
        for (int j = 0; j < numVertices; j++)
        {
            int weight;
            cin >> weight;
            graph.addEdge(i, j, weight);
        }
    }
    // Gragh类，其中有dijkstra方法是求最短路径的，接收一个起点参数，返回一个vector，是起点到各个顶点的最短路径，
    vector<int> distance = graph.dijkstra(0); //这里0作为起点
    vector<vector<int>> floyd_dist = graph.floyd();
    //for (int i = 0; i < distance.size(); i++)
    //{
    //    cout << distance[i] << endl; //输出起点到各个顶点的距离
    //}
    for (int i = 0; i < floyd_dist[0].size(); i++)
    {
        cout << floyd_dist[0][i] << endl; //输出起点到各个顶点的距离
    }
    system("pause");
    return 0;
}
