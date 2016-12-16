namespace ZavodyKraliku
{
    partial class DialogKralici
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
            this.groupBoxVolniKralici = new System.Windows.Forms.GroupBox();
            this.listBoxVolniKralici = new System.Windows.Forms.ListBox();
            this.groupBoxKralik = new System.Windows.Forms.GroupBox();
            this.buttonSmazat = new System.Windows.Forms.Button();
            this.buttonUpravit = new System.Windows.Forms.Button();
            this.labelJmeno = new System.Windows.Forms.Label();
            this.textBoxJmeno = new System.Windows.Forms.TextBox();
            this.buttonPridat = new System.Windows.Forms.Button();
            this.groupBoxLogovani = new System.Windows.Forms.GroupBox();
            this.listBoxLogovani = new System.Windows.Forms.ListBox();
            this.radioButtonNe = new System.Windows.Forms.RadioButton();
            this.radioButtonAno = new System.Windows.Forms.RadioButton();
            this.buttonOK = new System.Windows.Forms.Button();
            this.listBoxVsichniKralíci = new System.Windows.Forms.ListBox();
            this.groupBoxVsechniKralici = new System.Windows.Forms.GroupBox();
            this.groupBoxVolniKralici.SuspendLayout();
            this.groupBoxKralik.SuspendLayout();
            this.groupBoxLogovani.SuspendLayout();
            this.groupBoxVsechniKralici.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBoxVolniKralici
            // 
            this.groupBoxVolniKralici.Controls.Add(this.listBoxVolniKralici);
            this.groupBoxVolniKralici.Location = new System.Drawing.Point(229, 12);
            this.groupBoxVolniKralici.Name = "groupBoxVolniKralici";
            this.groupBoxVolniKralici.Size = new System.Drawing.Size(201, 229);
            this.groupBoxVolniKralici.TabIndex = 0;
            this.groupBoxVolniKralici.TabStop = false;
            this.groupBoxVolniKralici.Text = "Volní králíci";
            // 
            // listBoxVolniKralici
            // 
            this.listBoxVolniKralici.FormattingEnabled = true;
            this.listBoxVolniKralici.ItemHeight = 16;
            this.listBoxVolniKralici.Location = new System.Drawing.Point(6, 27);
            this.listBoxVolniKralici.Name = "listBoxVolniKralici";
            this.listBoxVolniKralici.Size = new System.Drawing.Size(190, 196);
            this.listBoxVolniKralici.TabIndex = 0;
            this.listBoxVolniKralici.SelectedIndexChanged += new System.EventHandler(this.listBoxVolniKralici_SelectedIndexChanged);
            // 
            // groupBoxKralik
            // 
            this.groupBoxKralik.Controls.Add(this.buttonSmazat);
            this.groupBoxKralik.Controls.Add(this.buttonUpravit);
            this.groupBoxKralik.Controls.Add(this.labelJmeno);
            this.groupBoxKralik.Controls.Add(this.textBoxJmeno);
            this.groupBoxKralik.Controls.Add(this.buttonPridat);
            this.groupBoxKralik.Location = new System.Drawing.Point(448, 12);
            this.groupBoxKralik.Name = "groupBoxKralik";
            this.groupBoxKralik.Size = new System.Drawing.Size(201, 190);
            this.groupBoxKralik.TabIndex = 1;
            this.groupBoxKralik.TabStop = false;
            this.groupBoxKralik.Text = "Králík";
            // 
            // buttonSmazat
            // 
            this.buttonSmazat.Enabled = false;
            this.buttonSmazat.Location = new System.Drawing.Point(60, 155);
            this.buttonSmazat.Name = "buttonSmazat";
            this.buttonSmazat.Size = new System.Drawing.Size(75, 28);
            this.buttonSmazat.TabIndex = 4;
            this.buttonSmazat.Text = "Smazat";
            this.buttonSmazat.UseVisualStyleBackColor = true;
            this.buttonSmazat.Click += new System.EventHandler(this.buttonSmazat_Click);
            // 
            // buttonUpravit
            // 
            this.buttonUpravit.Enabled = false;
            this.buttonUpravit.Location = new System.Drawing.Point(60, 121);
            this.buttonUpravit.Name = "buttonUpravit";
            this.buttonUpravit.Size = new System.Drawing.Size(75, 28);
            this.buttonUpravit.TabIndex = 3;
            this.buttonUpravit.Text = "Upravit";
            this.buttonUpravit.UseVisualStyleBackColor = true;
            this.buttonUpravit.Click += new System.EventHandler(this.buttonUpravit_Click);
            // 
            // labelJmeno
            // 
            this.labelJmeno.AutoSize = true;
            this.labelJmeno.Location = new System.Drawing.Point(17, 31);
            this.labelJmeno.Name = "labelJmeno";
            this.labelJmeno.Size = new System.Drawing.Size(50, 17);
            this.labelJmeno.TabIndex = 2;
            this.labelJmeno.Text = "Jméno";
            // 
            // textBoxJmeno
            // 
            this.textBoxJmeno.Location = new System.Drawing.Point(20, 59);
            this.textBoxJmeno.Name = "textBoxJmeno";
            this.textBoxJmeno.Size = new System.Drawing.Size(158, 22);
            this.textBoxJmeno.TabIndex = 1;
            // 
            // buttonPridat
            // 
            this.buttonPridat.Location = new System.Drawing.Point(60, 87);
            this.buttonPridat.Name = "buttonPridat";
            this.buttonPridat.Size = new System.Drawing.Size(75, 28);
            this.buttonPridat.TabIndex = 0;
            this.buttonPridat.Text = "Přidat";
            this.buttonPridat.UseVisualStyleBackColor = true;
            this.buttonPridat.Click += new System.EventHandler(this.buttonPridat_Click);
            // 
            // groupBoxLogovani
            // 
            this.groupBoxLogovani.Controls.Add(this.listBoxLogovani);
            this.groupBoxLogovani.Controls.Add(this.radioButtonNe);
            this.groupBoxLogovani.Controls.Add(this.radioButtonAno);
            this.groupBoxLogovani.Location = new System.Drawing.Point(667, 12);
            this.groupBoxLogovani.Name = "groupBoxLogovani";
            this.groupBoxLogovani.Size = new System.Drawing.Size(201, 229);
            this.groupBoxLogovani.TabIndex = 2;
            this.groupBoxLogovani.TabStop = false;
            this.groupBoxLogovani.Text = "Logování";
            // 
            // listBoxLogovani
            // 
            this.listBoxLogovani.FormattingEnabled = true;
            this.listBoxLogovani.ItemHeight = 16;
            this.listBoxLogovani.Location = new System.Drawing.Point(5, 59);
            this.listBoxLogovani.Name = "listBoxLogovani";
            this.listBoxLogovani.SelectionMode = System.Windows.Forms.SelectionMode.None;
            this.listBoxLogovani.Size = new System.Drawing.Size(190, 164);
            this.listBoxLogovani.TabIndex = 2;
            // 
            // radioButtonNe
            // 
            this.radioButtonNe.AutoSize = true;
            this.radioButtonNe.Location = new System.Drawing.Point(128, 27);
            this.radioButtonNe.Name = "radioButtonNe";
            this.radioButtonNe.Size = new System.Drawing.Size(47, 21);
            this.radioButtonNe.TabIndex = 1;
            this.radioButtonNe.Text = "Ne";
            this.radioButtonNe.UseVisualStyleBackColor = true;
            // 
            // radioButtonAno
            // 
            this.radioButtonAno.AutoSize = true;
            this.radioButtonAno.Checked = true;
            this.radioButtonAno.Location = new System.Drawing.Point(26, 27);
            this.radioButtonAno.Name = "radioButtonAno";
            this.radioButtonAno.Size = new System.Drawing.Size(54, 21);
            this.radioButtonAno.TabIndex = 0;
            this.radioButtonAno.TabStop = true;
            this.radioButtonAno.Text = "Ano";
            this.radioButtonAno.UseVisualStyleBackColor = true;
            this.radioButtonAno.CheckedChanged += new System.EventHandler(this.radioButtonAno_CheckedChanged);
            // 
            // buttonOK
            // 
            this.buttonOK.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.buttonOK.Location = new System.Drawing.Point(494, 213);
            this.buttonOK.Name = "buttonOK";
            this.buttonOK.Size = new System.Drawing.Size(103, 28);
            this.buttonOK.TabIndex = 5;
            this.buttonOK.Text = "OK";
            this.buttonOK.UseVisualStyleBackColor = true;
            // 
            // listBoxVsichniKralíci
            // 
            this.listBoxVsichniKralíci.FormattingEnabled = true;
            this.listBoxVsichniKralíci.ItemHeight = 16;
            this.listBoxVsichniKralíci.Location = new System.Drawing.Point(6, 27);
            this.listBoxVsichniKralíci.Name = "listBoxVsichniKralíci";
            this.listBoxVsichniKralíci.SelectionMode = System.Windows.Forms.SelectionMode.None;
            this.listBoxVsichniKralíci.Size = new System.Drawing.Size(190, 196);
            this.listBoxVsichniKralíci.TabIndex = 0;
            // 
            // groupBoxVsechniKralici
            // 
            this.groupBoxVsechniKralici.Controls.Add(this.listBoxVsichniKralíci);
            this.groupBoxVsechniKralici.Location = new System.Drawing.Point(12, 12);
            this.groupBoxVsechniKralici.Name = "groupBoxVsechniKralici";
            this.groupBoxVsechniKralici.Size = new System.Drawing.Size(201, 229);
            this.groupBoxVsechniKralici.TabIndex = 6;
            this.groupBoxVsechniKralici.TabStop = false;
            this.groupBoxVsechniKralici.Text = "Všichni králíci";
            // 
            // DialogKralici
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(888, 253);
            this.Controls.Add(this.groupBoxVsechniKralici);
            this.Controls.Add(this.buttonOK);
            this.Controls.Add(this.groupBoxLogovani);
            this.Controls.Add(this.groupBoxKralik);
            this.Controls.Add(this.groupBoxVolniKralici);
            this.MaximizeBox = false;
            this.MaximumSize = new System.Drawing.Size(906, 300);
            this.MinimizeBox = false;
            this.MinimumSize = new System.Drawing.Size(906, 300);
            this.Name = "DialogKralici";
            this.Text = "Správa Králíků";
            this.groupBoxVolniKralici.ResumeLayout(false);
            this.groupBoxKralik.ResumeLayout(false);
            this.groupBoxKralik.PerformLayout();
            this.groupBoxLogovani.ResumeLayout(false);
            this.groupBoxLogovani.PerformLayout();
            this.groupBoxVsechniKralici.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBoxVolniKralici;
        private System.Windows.Forms.GroupBox groupBoxKralik;
        private System.Windows.Forms.GroupBox groupBoxLogovani;
        private System.Windows.Forms.RadioButton radioButtonNe;
        private System.Windows.Forms.RadioButton radioButtonAno;
        private System.Windows.Forms.ListBox listBoxVolniKralici;
        private System.Windows.Forms.Button buttonSmazat;
        private System.Windows.Forms.Button buttonUpravit;
        private System.Windows.Forms.Label labelJmeno;
        private System.Windows.Forms.TextBox textBoxJmeno;
        private System.Windows.Forms.Button buttonPridat;
        private System.Windows.Forms.ListBox listBoxLogovani;
        private System.Windows.Forms.Button buttonOK;
        private System.Windows.Forms.ListBox listBoxVsichniKralíci;
        private System.Windows.Forms.GroupBox groupBoxVsechniKralici;
    }
}