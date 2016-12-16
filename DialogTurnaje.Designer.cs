namespace ZavodyKraliku
{
    partial class DialogTurnaje
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
            this.groupBoxTurnaje = new System.Windows.Forms.GroupBox();
            this.buttonSmazatTurnaj = new System.Windows.Forms.Button();
            this.buttonUpravitTurnaj = new System.Windows.Forms.Button();
            this.buttonVytvoritTurnaj = new System.Windows.Forms.Button();
            this.textBoxNazevTurnaje = new System.Windows.Forms.TextBox();
            this.labelNazevTurnaje = new System.Windows.Forms.Label();
            this.listBoxTurnaje = new System.Windows.Forms.ListBox();
            this.groupBoxKraliciVTurnaji = new System.Windows.Forms.GroupBox();
            this.buttonOdebratZTurnaje = new System.Windows.Forms.Button();
            this.listBoxKraliciTurnaj = new System.Windows.Forms.ListBox();
            this.listBoxVolniKralici = new System.Windows.Forms.ListBox();
            this.groupBoxVolniKralici = new System.Windows.Forms.GroupBox();
            this.buttonPridatDoTurnaje = new System.Windows.Forms.Button();
            this.buttonVymazatVse = new System.Windows.Forms.Button();
            this.buttonOK = new System.Windows.Forms.Button();
            this.groupBoxTurnaje.SuspendLayout();
            this.groupBoxKraliciVTurnaji.SuspendLayout();
            this.groupBoxVolniKralici.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBoxTurnaje
            // 
            this.groupBoxTurnaje.Controls.Add(this.buttonSmazatTurnaj);
            this.groupBoxTurnaje.Controls.Add(this.buttonUpravitTurnaj);
            this.groupBoxTurnaje.Controls.Add(this.buttonVytvoritTurnaj);
            this.groupBoxTurnaje.Controls.Add(this.textBoxNazevTurnaje);
            this.groupBoxTurnaje.Controls.Add(this.labelNazevTurnaje);
            this.groupBoxTurnaje.Controls.Add(this.listBoxTurnaje);
            this.groupBoxTurnaje.Location = new System.Drawing.Point(12, 26);
            this.groupBoxTurnaje.Name = "groupBoxTurnaje";
            this.groupBoxTurnaje.Size = new System.Drawing.Size(226, 307);
            this.groupBoxTurnaje.TabIndex = 0;
            this.groupBoxTurnaje.TabStop = false;
            this.groupBoxTurnaje.Text = "Turnaje";
            // 
            // buttonSmazatTurnaj
            // 
            this.buttonSmazatTurnaj.Enabled = false;
            this.buttonSmazatTurnaj.Location = new System.Drawing.Point(126, 264);
            this.buttonSmazatTurnaj.Name = "buttonSmazatTurnaj";
            this.buttonSmazatTurnaj.Size = new System.Drawing.Size(81, 27);
            this.buttonSmazatTurnaj.TabIndex = 5;
            this.buttonSmazatTurnaj.Text = "Smazat";
            this.buttonSmazatTurnaj.UseVisualStyleBackColor = true;
            this.buttonSmazatTurnaj.Click += new System.EventHandler(this.buttonSmazatTurnaj_Click);
            // 
            // buttonUpravitTurnaj
            // 
            this.buttonUpravitTurnaj.Enabled = false;
            this.buttonUpravitTurnaj.Location = new System.Drawing.Point(126, 231);
            this.buttonUpravitTurnaj.Name = "buttonUpravitTurnaj";
            this.buttonUpravitTurnaj.Size = new System.Drawing.Size(81, 27);
            this.buttonUpravitTurnaj.TabIndex = 4;
            this.buttonUpravitTurnaj.Text = "Upravit";
            this.buttonUpravitTurnaj.UseVisualStyleBackColor = true;
            this.buttonUpravitTurnaj.Click += new System.EventHandler(this.buttonUpravitTurnaj_Click);
            // 
            // buttonVytvoritTurnaj
            // 
            this.buttonVytvoritTurnaj.Location = new System.Drawing.Point(126, 198);
            this.buttonVytvoritTurnaj.Name = "buttonVytvoritTurnaj";
            this.buttonVytvoritTurnaj.Size = new System.Drawing.Size(81, 27);
            this.buttonVytvoritTurnaj.TabIndex = 3;
            this.buttonVytvoritTurnaj.Text = "Vytvořit";
            this.buttonVytvoritTurnaj.UseVisualStyleBackColor = true;
            this.buttonVytvoritTurnaj.Click += new System.EventHandler(this.buttonVytvoritTurnaj_Click);
            // 
            // textBoxNazevTurnaje
            // 
            this.textBoxNazevTurnaje.Location = new System.Drawing.Point(17, 233);
            this.textBoxNazevTurnaje.Name = "textBoxNazevTurnaje";
            this.textBoxNazevTurnaje.Size = new System.Drawing.Size(93, 22);
            this.textBoxNazevTurnaje.TabIndex = 2;
            // 
            // labelNazevTurnaje
            // 
            this.labelNazevTurnaje.AutoSize = true;
            this.labelNazevTurnaje.Location = new System.Drawing.Point(14, 203);
            this.labelNazevTurnaje.Name = "labelNazevTurnaje";
            this.labelNazevTurnaje.Size = new System.Drawing.Size(96, 17);
            this.labelNazevTurnaje.TabIndex = 1;
            this.labelNazevTurnaje.Text = "Název turnaje";
            // 
            // listBoxTurnaje
            // 
            this.listBoxTurnaje.FormattingEnabled = true;
            this.listBoxTurnaje.ItemHeight = 16;
            this.listBoxTurnaje.Location = new System.Drawing.Point(17, 21);
            this.listBoxTurnaje.Name = "listBoxTurnaje";
            this.listBoxTurnaje.Size = new System.Drawing.Size(190, 148);
            this.listBoxTurnaje.TabIndex = 0;
            this.listBoxTurnaje.SelectedIndexChanged += new System.EventHandler(this.listBoxTurnaje_SelectedIndexChanged);
            // 
            // groupBoxKraliciVTurnaji
            // 
            this.groupBoxKraliciVTurnaji.Controls.Add(this.buttonOdebratZTurnaje);
            this.groupBoxKraliciVTurnaji.Controls.Add(this.listBoxKraliciTurnaj);
            this.groupBoxKraliciVTurnaji.Location = new System.Drawing.Point(253, 26);
            this.groupBoxKraliciVTurnaji.Name = "groupBoxKraliciVTurnaji";
            this.groupBoxKraliciVTurnaji.Size = new System.Drawing.Size(226, 244);
            this.groupBoxKraliciVTurnaji.TabIndex = 1;
            this.groupBoxKraliciVTurnaji.TabStop = false;
            this.groupBoxKraliciVTurnaji.Text = "Králíci v turnaji";
            // 
            // buttonOdebratZTurnaje
            // 
            this.buttonOdebratZTurnaje.Enabled = false;
            this.buttonOdebratZTurnaje.Location = new System.Drawing.Point(17, 192);
            this.buttonOdebratZTurnaje.Name = "buttonOdebratZTurnaje";
            this.buttonOdebratZTurnaje.Size = new System.Drawing.Size(190, 38);
            this.buttonOdebratZTurnaje.TabIndex = 2;
            this.buttonOdebratZTurnaje.Text = "Odebrat z turnaje";
            this.buttonOdebratZTurnaje.UseVisualStyleBackColor = true;
            this.buttonOdebratZTurnaje.Click += new System.EventHandler(this.buttonOdebratZTurnaje_Click);
            // 
            // listBoxKraliciTurnaj
            // 
            this.listBoxKraliciTurnaj.FormattingEnabled = true;
            this.listBoxKraliciTurnaj.ItemHeight = 16;
            this.listBoxKraliciTurnaj.Location = new System.Drawing.Point(17, 21);
            this.listBoxKraliciTurnaj.Name = "listBoxKraliciTurnaj";
            this.listBoxKraliciTurnaj.Size = new System.Drawing.Size(190, 148);
            this.listBoxKraliciTurnaj.TabIndex = 0;
            this.listBoxKraliciTurnaj.SelectedIndexChanged += new System.EventHandler(this.listBoxKraliciTurnaj_SelectedIndexChanged);
            // 
            // listBoxVolniKralici
            // 
            this.listBoxVolniKralici.FormattingEnabled = true;
            this.listBoxVolniKralici.ItemHeight = 16;
            this.listBoxVolniKralici.Location = new System.Drawing.Point(17, 21);
            this.listBoxVolniKralici.Name = "listBoxVolniKralici";
            this.listBoxVolniKralici.Size = new System.Drawing.Size(190, 148);
            this.listBoxVolniKralici.TabIndex = 0;
            this.listBoxVolniKralici.SelectedIndexChanged += new System.EventHandler(this.listBoxVolniKralici_SelectedIndexChanged);
            // 
            // groupBoxVolniKralici
            // 
            this.groupBoxVolniKralici.Controls.Add(this.buttonPridatDoTurnaje);
            this.groupBoxVolniKralici.Controls.Add(this.listBoxVolniKralici);
            this.groupBoxVolniKralici.Location = new System.Drawing.Point(496, 26);
            this.groupBoxVolniKralici.Name = "groupBoxVolniKralici";
            this.groupBoxVolniKralici.Size = new System.Drawing.Size(226, 244);
            this.groupBoxVolniKralici.TabIndex = 2;
            this.groupBoxVolniKralici.TabStop = false;
            this.groupBoxVolniKralici.Text = "Volní králíci";
            // 
            // buttonPridatDoTurnaje
            // 
            this.buttonPridatDoTurnaje.Enabled = false;
            this.buttonPridatDoTurnaje.Location = new System.Drawing.Point(17, 192);
            this.buttonPridatDoTurnaje.Name = "buttonPridatDoTurnaje";
            this.buttonPridatDoTurnaje.Size = new System.Drawing.Size(190, 38);
            this.buttonPridatDoTurnaje.TabIndex = 1;
            this.buttonPridatDoTurnaje.Text = "Přidat do turnaje";
            this.buttonPridatDoTurnaje.UseVisualStyleBackColor = true;
            this.buttonPridatDoTurnaje.Click += new System.EventHandler(this.buttonPridatDoTurnaje_Click);
            // 
            // buttonVymazatVse
            // 
            this.buttonVymazatVse.Location = new System.Drawing.Point(270, 290);
            this.buttonVymazatVse.Name = "buttonVymazatVse";
            this.buttonVymazatVse.Size = new System.Drawing.Size(190, 40);
            this.buttonVymazatVse.TabIndex = 3;
            this.buttonVymazatVse.Text = "Vymazat turnaje a králíky";
            this.buttonVymazatVse.UseVisualStyleBackColor = true;
            this.buttonVymazatVse.Click += new System.EventHandler(this.buttonVymazatVse_Click);
            // 
            // buttonOK
            // 
            this.buttonOK.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.buttonOK.Location = new System.Drawing.Point(554, 296);
            this.buttonOK.Name = "buttonOK";
            this.buttonOK.Size = new System.Drawing.Size(103, 28);
            this.buttonOK.TabIndex = 6;
            this.buttonOK.Text = "OK";
            this.buttonOK.UseVisualStyleBackColor = true;
            // 
            // DialogTurnaje
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(742, 366);
            this.Controls.Add(this.buttonOK);
            this.Controls.Add(this.buttonVymazatVse);
            this.Controls.Add(this.groupBoxVolniKralici);
            this.Controls.Add(this.groupBoxKraliciVTurnaji);
            this.Controls.Add(this.groupBoxTurnaje);
            this.MaximizeBox = false;
            this.MaximumSize = new System.Drawing.Size(760, 413);
            this.MinimizeBox = false;
            this.MinimumSize = new System.Drawing.Size(760, 413);
            this.Name = "DialogTurnaje";
            this.Text = "Správa Turnajů";
            this.groupBoxTurnaje.ResumeLayout(false);
            this.groupBoxTurnaje.PerformLayout();
            this.groupBoxKraliciVTurnaji.ResumeLayout(false);
            this.groupBoxVolniKralici.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBoxTurnaje;
        private System.Windows.Forms.ListBox listBoxTurnaje;
        private System.Windows.Forms.GroupBox groupBoxKraliciVTurnaji;
        private System.Windows.Forms.ListBox listBoxKraliciTurnaj;
        private System.Windows.Forms.ListBox listBoxVolniKralici;
        private System.Windows.Forms.GroupBox groupBoxVolniKralici;
        private System.Windows.Forms.Label labelNazevTurnaje;
        private System.Windows.Forms.Button buttonOdebratZTurnaje;
        private System.Windows.Forms.Button buttonPridatDoTurnaje;
        private System.Windows.Forms.Button buttonVytvoritTurnaj;
        private System.Windows.Forms.TextBox textBoxNazevTurnaje;
        private System.Windows.Forms.Button buttonSmazatTurnaj;
        private System.Windows.Forms.Button buttonUpravitTurnaj;
        private System.Windows.Forms.Button buttonVymazatVse;
        private System.Windows.Forms.Button buttonOK;
    }
}