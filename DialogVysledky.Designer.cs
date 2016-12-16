namespace ZavodyKraliku
{
    partial class DialogVysledky
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.listBoxVysledky = new System.Windows.Forms.ListBox();
            this.groupBoxVysledky = new System.Windows.Forms.GroupBox();
            this.buttonOK = new System.Windows.Forms.Button();
            this.groupBoxVysledky.SuspendLayout();
            this.SuspendLayout();
            // 
            // listBoxVysledky
            // 
            this.listBoxVysledky.Font = new System.Drawing.Font("Tempus Sans ITC", 10.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.listBoxVysledky.FormattingEnabled = true;
            this.listBoxVysledky.ItemHeight = 22;
            this.listBoxVysledky.Location = new System.Drawing.Point(25, 42);
            this.listBoxVysledky.Name = "listBoxVysledky";
            this.listBoxVysledky.SelectionMode = System.Windows.Forms.SelectionMode.None;
            this.listBoxVysledky.Size = new System.Drawing.Size(1079, 312);
            this.listBoxVysledky.TabIndex = 0;
            // 
            // groupBoxVysledky
            // 
            this.groupBoxVysledky.Controls.Add(this.listBoxVysledky);
            this.groupBoxVysledky.Font = new System.Drawing.Font("Tempus Sans ITC", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.groupBoxVysledky.ForeColor = System.Drawing.Color.Green;
            this.groupBoxVysledky.Location = new System.Drawing.Point(37, 34);
            this.groupBoxVysledky.Name = "groupBoxVysledky";
            this.groupBoxVysledky.Size = new System.Drawing.Size(1127, 375);
            this.groupBoxVysledky.TabIndex = 1;
            this.groupBoxVysledky.TabStop = false;
            this.groupBoxVysledky.Text = "groupBox1";
            // 
            // buttonOK
            // 
            this.buttonOK.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.buttonOK.Location = new System.Drawing.Point(524, 415);
            this.buttonOK.Name = "buttonOK";
            this.buttonOK.Size = new System.Drawing.Size(103, 28);
            this.buttonOK.TabIndex = 6;
            this.buttonOK.Text = "OK";
            this.buttonOK.UseVisualStyleBackColor = true;
            // 
            // DialogVysledky
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1198, 452);
            this.Controls.Add(this.buttonOK);
            this.Controls.Add(this.groupBoxVysledky);
            this.MaximizeBox = false;
            this.MaximumSize = new System.Drawing.Size(1216, 499);
            this.MinimizeBox = false;
            this.MinimumSize = new System.Drawing.Size(1216, 499);
            this.Name = "DialogVysledky";
            this.Text = "Výsledky Turnajů";
            this.groupBoxVysledky.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListBox listBoxVysledky;
        private System.Windows.Forms.GroupBox groupBoxVysledky;
        private System.Windows.Forms.Button buttonOK;
    }
}