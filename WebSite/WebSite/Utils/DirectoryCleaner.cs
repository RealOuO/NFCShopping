using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.IO;

namespace NFCShoppingWebSite.Utils
{
    public class DirectoryCleaner
    {
        public static void CleanDirectory(string directory)
        {
            try
            {
                string[] tempFiles = Directory.GetFiles(directory);

                foreach (string tempFile in tempFiles)
                {
                    File.Delete(tempFile);
                }
            }
            catch (Exception ex)
            {
                // TODO: Add handling code here.
            }
        }
    }
}