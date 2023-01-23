namespace OnScreenKeyboardNavigator
{
    public partial class MainForm : Form
    {
        private Keyboard _keys;

        public MainForm()
        {
            InitializeComponent();
            _keys = new Keyboard();
        }

        /// <summary>
        /// Parses through selected file and attempts to generate keyboard navigation paths for every line.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void selectFileButton_Click(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog
            {
                Title = "Browse",
            };

            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                dataGridView.Rows.Clear();
                selectedFileLabel.Text = openFileDialog.FileName;
                foreach (string s in File.ReadLines(openFileDialog.FileName))
                {
                    int index = dataGridView.Rows.Add();
                    dataGridView.Rows[index].Cells[0].Value = s;
                    dataGridView.Rows[index].Cells[1].Value = GeneratePath(s);
                }
            }
        }

        /// <summary>
        /// Allows user to manually type in stream of characters to generate navigation path.
        ///
        /// Maybe implement debounce someday
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void textBox_TextChanged(object sender, EventArgs e)
        {
            dataGridView.Rows.Clear();
            int index = dataGridView.Rows.Add();
            dataGridView.Rows[index].Cells[0].Value = textBox.Text;
            dataGridView.Rows[index].Cells[1].Value = GeneratePath(textBox.Text);
        }

        /// <summary>
        /// Generates path on the on screen board.
        /// </summary>
        /// <param name="text"></param>
        /// <returns></returns>
        private string GeneratePath(string text)
        {
            text = text.ToUpper();

            var path = string.Empty;
            var cursor = new int[] { 0, 0 };

            for (var i = 0; i < text.Length; i++)
            {
                if (text[i] == ' ')
                {
                    path += 'S';
                    continue;
                }

                var charCoord = _keys.GetKey(text[i]);

                // Calculate y-axis change
                var vertDiff = charCoord[1] - cursor[1];
                if (vertDiff > 0)
                {
                    path += new string('D', Math.Abs(vertDiff));
                }
                else if (vertDiff < 0)
                {
                    path += new string('U', Math.Abs(vertDiff));
                }

                // Calculate x-axis change
                var horizDiff = charCoord[0] - cursor[0];
                if (horizDiff > 0)
                {
                    path += new string('R', Math.Abs(horizDiff));
                }
                else if (horizDiff < 0)
                {
                    path += new string('L', Math.Abs(horizDiff));
                }

                path += '#';

                cursor = charCoord;
            }

            return string.Join(",", path.ToCharArray());
        }
    }
}