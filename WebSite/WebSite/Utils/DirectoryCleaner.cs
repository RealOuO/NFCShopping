using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.IO;

namespace NFCShoppingWebSite.Utils
{
    public class DirectoryCleaner
    {
        /**
         *  Clean up a given directory.
         *  @param directory The directory to clean.
         */
        public static void CleanDirectory(string directory)
        {
            try
            {
                string[] tempFiles = Directory.GetFiles(directory);

                // Delete each file in the given directory.
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