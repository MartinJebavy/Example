namespace ZavodyKraliku
{
    partial class Aplikace
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
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.labelVolniKralici = new System.Windows.Forms.Label();
            this.labelKraliciTurnaj = new System.Windows.Forms.Label();
            this.labelTurnaje = new System.Windows.Forms.Label();
            this.listBoxVolniKralici = new System.Windows.Forms.ListBox();
            this.listBoxKraliciTurnaj = new System.Windows.Forms.ListBox();
            this.listBoxTurnaje = new System.Windows.Forms.ListBox();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.toolStripMenuSoubor = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuUlozit = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuNacist = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItemUkoncit = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuSprava = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuKralici = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuTurnaje = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItemVysledky = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItemRestartovatTurnaje = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItemZachovatUcastniky = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItemVymazatUcasniky = new System.Windows.Forms.ToolStripMenuItem();
            this.buttonOdehrat = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.labelVolniKralici);
            this.groupBox1.Controls.Add(this.labelKraliciTurnaj);
            this.groupBox1.Controls.Add(this.labelTurnaje);
            this.groupBox1.Controls.Add(this.listBoxVolniKralici);
            this.groupBox1.Controls.Add(this.listBoxKraliciTurnaj);
            this.groupBox1.Controls.Add(this.listBoxTurnaje);
            this.groupBox1.Location = new System.Drawing.Point(12, 38);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(808, 276);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Soupiska";
            // 
            // labelVolniKralici
            // 
            this.labelVolniKralici.AutoSize = true;
            this.labelVolniKralici.Location = new System.Drawing.Point(546, 27);
            this.labelVolniKralici.Name = "labelVolniKralici";
            this.labelVolniKralici.Size = new System.Drawing.Size(79, 17);
            this.labelVolniKralici.TabIndex = 5;
            this.labelVolniKralici.Text = "Volní králici";
            // 
            // labelKraliciTurnaj
            // 
            this.labelKraliciTurnaj.AutoSize = true;
            this.labelKraliciTurnaj.Location = new System.Drawing.Point(283, 27);
            this.labelKraliciTurnaj.Name = "labelKraliciTurnaj";
            this.labelKraliciTurnaj.Size = new System.Drawing.Size(100, 17);
            this.labelKraliciTurnaj.TabIndex = 4;
            this.labelKraliciTurnaj.Text = "Králíci v turnaji";
            // 
            // labelTurnaje
            // 
            this.labelTurnaje.AutoSize = true;
            this.labelTurnaje.Location = new System.Drawing.Point(20, 27);
            this.labelTurnaje.Name = "labelTurnaje";
            this.labelTurnaje.Size = new System.Drawing.Size(57, 17);
            this.labelTurnaje.TabIndex = 3;
            this.labelTurnaje.Text = "Turnaje";
            // 
            // listBoxVolniKralici
            // 
            this.listBoxVolniKralici.FormattingEnabled = true;
            this.listBoxVolniKralici.ItemHeight = 16;
            this.listBoxVolniKralici.Location = new System.Drawing.Point(549, 56);
            this.listBoxVolniKralici.Name = "listBoxVolniKralici";
            this.listBoxVolniKralici.SelectionMode = System.Windows.Forms.SelectionMode.None;
            this.listBoxVolniKralici.Size = new System.Drawing.Size(233, 196);
            this.listBoxVolniKralici.TabIndex = 2;
            // 
            // listBoxKraliciTurnaj
            // 
            this.listBoxKraliciTurnaj.FormattingEnabled = true;
            this.listBoxKraliciTurnaj.ItemHeight = 16;
            this.listBoxKraliciTurnaj.Location = new System.Drawing.Point(286, 56);
            this.listBoxKraliciTurnaj.Name = "listBoxKraliciTurnaj";
            this.listBoxKraliciTurnaj.SelectionMode = System.Windows.Forms.SelectionMode.None;
            this.listBoxKraliciTurnaj.Size = new System.Drawing.Size(233, 196);
            this.listBoxKraliciTurnaj.TabIndex = 1;
            // 
            // listBoxTurnaje
            // 
            this.listBoxTurnaje.FormattingEnabled = true;
            this.listBoxTurnaje.ItemHeight = 16;
            this.listBoxTurnaje.Location = new System.Drawing.Point(23, 56);
            this.listBoxTurnaje.Name = "listBoxTurnaje";
            this.listBoxTurnaje.Size = new System.Drawing.Size(233, 196);
            this.listBoxTurnaje.TabIndex = 0;
            this.listBoxTurnaje.SelectedIndexChanged += new System.EventHandler(this.listBoxTurnaje_SelectedIndexChanged);
            // 
            // menuStrip1
            // 
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripMenuSoubor,
            this.toolStripMenuSprava,
            this.toolStripMenuItemVysledky,
            this.toolStripMenuItemRestartovatTurnaje});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(836, 28);
            this.menuStrip1.TabIndex = 1;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // toolStripMenuSoubor
            // 
            this.toolStripMenuSoubor.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripMenuUlozit,
            this.toolStripMenuNacist,
            this.toolStripMenuItemUkoncit});
            this.toolStripMenuSoubor.Name = "toolStripMenuSoubor";
            this.toolStripMenuSoubor.Size = new System.Drawing.Size(69, 24);
            this.toolStripMenuSoubor.Text = "Soubor";
            // 
            // toolStripMenuUlozit
            // 
            this.toolStripMenuUlozit.Name = "toolStripMenuUlozit";
            this.toolStripMenuUlozit.Size = new System.Drawing.Size(128, 24);
            this.toolStripMenuUlozit.Text = "Uložit";
            this.toolStripMenuUlozit.Click += new System.EventHandler(this.toolStripMenuUlozit_Click);
            // 
            // toolStripMenuNacist
            // 
            this.toolStripMenuNacist.Name = "toolStripMenuNacist";
            this.toolStripMenuNacist.Size = new System.Drawing.Size(128, 24);
            this.toolStripMenuNacist.Text = "Načíst";
            this.toolStripMenuNacist.Click += new System.EventHandler(this.toolStripMenuNacist_Click);
            // 
            // toolStripMenuItemUkoncit
            // 
            this.toolStripMenuItemUkoncit.Name = "toolStripMenuItemUkoncit";
            this.toolStripMenuItemUkoncit.Size = new System.Drawing.Size(128, 24);
            this.toolStripMenuItemUkoncit.Text = "Ukončit";
            this.toolStripMenuItemUkoncit.Click += new System.EventHandler(this.toolStripMenuItemUkoncit_Click);
            // 
            // toolStripMenuSprava
            // 
            this.toolStripMenuSprava.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripMenuKralici,
            this.toolStripMenuTurnaje});
            this.toolStripMenuSprava.Name = "toolStripMenuSprava";
            this.toolStripMenuSprava.Size = new System.Drawing.Size(66, 24);
            this.toolStripMenuSprava.Text = "Správa";
            // 
            // toolStripMenuKralici
            // 
            this.toolStripMenuKralici.Name = "toolStripMenuKralici";
            this.toolStripMenuKralici.Size = new System.Drawing.Size(127, 24);
            this.toolStripMenuKralici.Text = "Králíci";
            this.toolStripMenuKralici.Click += new System.EventHandler(this.toolStripMenuKralici_Click);
            // 
            // toolStripMenuTurnaje
            // 
            this.toolStripMenuTurnaje.Name = "toolStripMenuTurnaje";
            this.toolStripMenuTurnaje.Size = new System.Drawing.Size(127, 24);
            this.toolStripMenuTurnaje.Text = "Turnaje";
            this.toolStripMenuTurnaje.Click += new System.EventHandler(this.toolStripMenuTurnaje_Click);
            // 
            // toolStripMenuItemVysledky
            // 
            this.toolStripMenuItemVysledky.Enabled = false;
            this.toolStripMenuItemVysledky.Name = "toolStripMenuItemVysledky";
            this.toolStripMenuItemVysledky.Size = new System.Drawing.Size(131, 24);
            this.toolStripMenuItemVysledky.Text = "Výsledky Turnajů";
            this.toolStripMenuItemVysledky.Click += new System.EventHandler(this.toolStripMenuItemVysledky_Click);
            // 
            // toolStripMenuItemRestartovatTurnaje
            // 
            this.toolStripMenuItemRestartovatTurnaje.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripMenuItemZachovatUcastniky,
            this.toolStripMenuItemVymazatUcasniky});
            this.toolStripMenuItemRestartovatTurnaje.Enabled = false;
            this.toolStripMenuItemRestartovatTurnaje.Name = "toolStripMenuItemRestartovatTurnaje";
            this.toolStripMenuItemRestartovatTurnaje.Size = new System.Drawing.Size(149, 24);
            this.toolStripMenuItemRestartovatTurnaje.Text = "Restartovat Turnaje";
            // 
            // toolStripMenuItemZachovatUcastniky
            // 
            this.toolStripMenuItemZachovatUcastniky.Name = "toolStripMenuItemZachovatUcastniky";
            this.toolStripMenuItemZachovatUcastniky.Size = new System.Drawing.Size(205, 24);
            this.toolStripMenuItemZachovatUcastniky.Text = "Zachovat Učastníky";
            this.toolStripMenuItemZachovatUcastniky.Click += new System.EventHandler(this.toolStripMenuItemZachovatUcastniky_Click);
            // 
            // toolStripMenuItemVymazatUcasniky
            // 
            this.toolStripMenuItemVymazatUcasniky.Name = "toolStripMenuItemVymazatUcasniky";
            this.toolStripMenuItemVymazatUcasniky.Size = new System.Drawing.Size(205, 24);
            this.toolStripMenuItemVymazatUcasniky.Text = "Vymazat Učastníky";
            this.toolStripMenuItemVymazatUcasniky.Click += new System.EventHandler(this.toolStripMenuItemVymazatUcasniky_Click);
            // 
            // buttonOdehrat
            // 
            this.buttonOdehrat.Enabled = false;
            this.buttonOdehrat.Font = new System.Drawing.Font("Tempus Sans ITC", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.buttonOdehrat.ForeColor = System.Drawing.Color.Green;
            this.buttonOdehrat.Location = new System.Drawing.Point(298, 321);
            this.buttonOdehrat.Name = "buttonOdehrat";
            this.buttonOdehrat.Size = new System.Drawing.Size(233, 50);
            this.buttonOdehrat.TabIndex = 2;
            this.buttonOdehrat.Text = "Odehrát turnaje";
            this.buttonOdehrat.UseVisualStyleBackColor = true;
            this.buttonOdehrat.Click += new System.EventHandler(this.buttonOdehrat_Click);
            // 
            // Aplikace
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(836, 388);
            this.Controls.Add(this.buttonOdehrat);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.MaximizeBox = false;
            this.MaximumSize = new System.Drawing.Size(854, 435);
            this.MinimumSize = new System.Drawing.Size(854, 435);
            this.Name = "Aplikace";
            this.Text = "Turnaj Králíků";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuSoubor;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuUlozit;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuNacist;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuSprava;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuKralici;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuTurnaje;
        private System.Windows.Forms.ListBox listBoxVolniKralici;
        private System.Windows.Forms.ListBox listBoxKraliciTurnaj;
        private System.Windows.Forms.ListBox listBoxTurnaje;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItemUkoncit;
        private System.Windows.Forms.Label labelVolniKralici;
        private System.Windows.Forms.Label labelKraliciTurnaj;
        private System.Windows.Forms.Label labelTurnaje;
        private System.Windows.Forms.Button buttonOdehrat;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItemVysledky;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItemRestartovatTurnaje;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItemZachovatUcastniky;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItemVymazatUcasniky;
    }
}

