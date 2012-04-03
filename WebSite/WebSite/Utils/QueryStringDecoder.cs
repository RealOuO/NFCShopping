using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NFCShoppingWebSite.Utils
{
    public class QueryStringDecoder
    {
        /**
         *  Decodes a given query string.
         *  @param queryString The given query string.
         *  @param separator The given separator to decode the string.
         *  @returns The decoded pairs. e.q. Query string is "key=value" and the decoded pair will be <key, value>
         */
        public static Dictionary<string, string> Decode(String queryString, string[] separator)
        {
            Dictionary<string, string> dictionary = new Dictionary<string, string>();
            // Split the query string by using the given separator.
            string[] queryPairs = queryString.Split(separator, StringSplitOptions.RemoveEmptyEntries);

            foreach (string queryPair in queryPairs)
            {
                // Split the query pair by "=".
                string[] keyAndValue = queryPair.Split(new string[] { "=" }, StringSplitOptions.RemoveEmptyEntries);

                // Filter the abnormal results.
                if (keyAndValue.Length == 2)
                {
                    dictionary.Add(keyAndValue[0], keyAndValue[1]);
                }
            }

            return dictionary;
        }
    }
}