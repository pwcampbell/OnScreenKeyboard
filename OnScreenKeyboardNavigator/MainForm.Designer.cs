namespace OnScreenKeyboardNavigator
{
    partial class MainForm
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.selectFileButton = new System.Windows.Forms.Button();
            this.orLabel = new System.Windows.Forms.Label();
            this.textBox = new System.Windows.Forms.TextBox();
            this.selectedFileTextLabel = new System.Windows.Forms.Label();
            this.enterTextLabel = new System.Windows.Forms.Label();
            this.selectedFileLabel = new System.Windows.Forms.Label();
            this.dataGridView = new System.Windows.Forms.DataGridView();
            this.Column1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Column2 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            this.SuspendLayout();
            // 
            // selectFileButton
            // 
            this.selectFileButton.Location = new System.Drawing.Point(12, 12);
            this.selectFileButton.Name = "selectFileButton";
            this.selectFileButton.Size = new System.Drawing.Size(94, 29);
            this.selectFileButton.TabIndex = 0;
            this.selectFileButton.Text = "Select File";
            this.selectFileButton.UseVisualStyleBackColor = true;
            this.selectFileButton.Click += new System.EventHandler(this.selectFileButton_Click);
            // 
            // orLabel
            // 
            this.orLabel.AutoSize = true;
            this.orLabel.Location = new System.Drawing.Point(40, 50);
            this.orLabel.Name = "orLabel";
            this.orLabel.Size = new System.Drawing.Size(29, 20);
            this.orLabel.TabIndex = 1;
            this.orLabel.Text = "OR";
            // 
            // textBox
            // 
            this.textBox.Location = new System.Drawing.Point(95, 75);
            this.textBox.Name = "textBox";
            this.textBox.Size = new System.Drawing.Size(753, 27);
            this.textBox.TabIndex = 2;
            this.textBox.TextChanged += new System.EventHandler(this.textBox_TextChanged);
            // 
            // selectedFileTextLabel
            // 
            this.selectedFileTextLabel.AutoSize = true;
            this.selectedFileTextLabel.Location = new System.Drawing.Point(112, 16);
            this.selectedFileTextLabel.Name = "selectedFileTextLabel";
            this.selectedFileTextLabel.Size = new System.Drawing.Size(100, 20);
            this.selectedFileTextLabel.TabIndex = 3;
            this.selectedFileTextLabel.Text = "Selected File: ";
            // 
            // enterTextLabel
            // 
            this.enterTextLabel.AutoSize = true;
            this.enterTextLabel.Location = new System.Drawing.Point(12, 78);
            this.enterTextLabel.Name = "enterTextLabel";
            this.enterTextLabel.Size = new System.Drawing.Size(77, 20);
            this.enterTextLabel.TabIndex = 4;
            this.enterTextLabel.Text = "Enter Text:";
            // 
            // selectedFileLabel
            // 
            this.selectedFileLabel.AutoSize = true;
            this.selectedFileLabel.Location = new System.Drawing.Point(211, 16);
            this.selectedFileLabel.Name = "selectedFileLabel";
            this.selectedFileLabel.Size = new System.Drawing.Size(0, 20);
            this.selectedFileLabel.TabIndex = 5;
            // 
            // dataGridView
            // 
            this.dataGridView.BackgroundColor = System.Drawing.SystemColors.ControlLight;
            this.dataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.Column1,
            this.Column2});
            this.dataGridView.Location = new System.Drawing.Point(12, 124);
            this.dataGridView.Name = "dataGridView";
            this.dataGridView.RowHeadersWidth = 51;
            this.dataGridView.RowTemplate.Height = 29;
            this.dataGridView.Size = new System.Drawing.Size(836, 438);
            this.dataGridView.TabIndex = 6;
            // 
            // Column1
            // 
            this.Column1.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.Fill;
            this.Column1.HeaderText = "Input Text";
            this.Column1.MinimumWidth = 6;
            this.Column1.Name = "Column1";
            // 
            // Column2
            // 
            this.Column2.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.Fill;
            this.Column2.HeaderText = "Output Commands";
            this.Column2.MinimumWidth = 6;
            this.Column2.Name = "Column2";
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(860, 574);
            this.Controls.Add(this.dataGridView);
            this.Controls.Add(this.selectedFileLabel);
            this.Controls.Add(this.enterTextLabel);
            this.Controls.Add(this.selectedFileTextLabel);
            this.Controls.Add(this.textBox);
            this.Controls.Add(this.orLabel);
            this.Controls.Add(this.selectFileButton);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "MainForm";
            this.Text = "On Screen Keyboard Navigator";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Button selectFileButton;
        private Label orLabel;
        private TextBox textBox;
        private Label selectedFileTextLabel;
        private Label enterTextLabel;
        private Label selectedFileLabel;
        private DataGridView dataGridView;
        private DataGridViewTextBoxColumn Column1;
        private DataGridViewTextBoxColumn Column2;
    }
}