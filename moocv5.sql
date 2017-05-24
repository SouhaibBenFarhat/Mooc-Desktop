-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 12 Mai 2016 à 17:57
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `moocv5`
--

-- --------------------------------------------------------

--
-- Structure de la table `aime`
--

CREATE TABLE IF NOT EXISTS `aime` (
  `id_aime` int(11) NOT NULL AUTO_INCREMENT,
  `id_sujet_aime` int(11) DEFAULT NULL,
  `id_utilisateur_aime` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_aime`),
  KEY `IDX_A86190D67A0878A8` (`id_sujet_aime`),
  KEY `IDX_A86190D622580603` (`id_utilisateur_aime`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=70 ;

--
-- Contenu de la table `aime`
--

INSERT INTO `aime` (`id_aime`, `id_sujet_aime`, `id_utilisateur_aime`) VALUES
(62, 4, 19),
(63, 2, 19),
(64, 3, 19),
(66, 4, 20),
(68, 11, 20),
(69, 11, 21);

-- --------------------------------------------------------

--
-- Structure de la table `appreciation`
--

CREATE TABLE IF NOT EXISTS `appreciation` (
  `id_appreciation` int(11) NOT NULL AUTO_INCREMENT,
  `id_apprenant_appreciation` int(11) DEFAULT NULL,
  `id_cours_appreciation` int(11) DEFAULT NULL,
  `valeur_appreciation` longtext COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_appreciation`),
  KEY `IDX_5CD4DEABE5D8D4F8` (`id_apprenant_appreciation`),
  KEY `IDX_5CD4DEABC8036527` (`id_cours_appreciation`),
  KEY `id_cours` (`id_cours_appreciation`,`id_apprenant_appreciation`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Contenu de la table `appreciation`
--

INSERT INTO `appreciation` (`id_appreciation`, `id_apprenant_appreciation`, `id_cours_appreciation`, `valeur_appreciation`) VALUES
(2, 20, 10, '<p>Quelque chose vas mal dans ce cours</p>\r\n');

-- --------------------------------------------------------

--
-- Structure de la table `bibliotheque`
--

CREATE TABLE IF NOT EXISTS `bibliotheque` (
  `id_bibliotheque` int(11) NOT NULL AUTO_INCREMENT,
  `id_cours_bibliotheque` int(11) DEFAULT NULL,
  `id_apprenant_bibliotheque` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_bibliotheque`),
  UNIQUE KEY `id_apprenant_bibliotheque` (`id_apprenant_bibliotheque`,`id_cours_bibliotheque`),
  KEY `IDX_4690D34DD24768C1` (`id_cours_bibliotheque`),
  KEY `IDX_4690D34DFF9CD91E` (`id_apprenant_bibliotheque`),
  KEY `id_cours_bibliotheque` (`id_cours_bibliotheque`,`id_apprenant_bibliotheque`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=100 ;

--
-- Contenu de la table `bibliotheque`
--

INSERT INTO `bibliotheque` (`id_bibliotheque`, `id_cours_bibliotheque`, `id_apprenant_bibliotheque`) VALUES
(11, 9, 20),
(12, 10, 20),
(13, 11, 20),
(64, 16, 20),
(96, 9, 66),
(99, 11, 66),
(98, 16, 66);

-- --------------------------------------------------------

--
-- Structure de la table `chapitre`
--

CREATE TABLE IF NOT EXISTS `chapitre` (
  `id_chapitre` int(11) NOT NULL AUTO_INCREMENT,
  `id_cours_chapitre` int(11) DEFAULT NULL,
  `titre_chapitre` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `description_chapitre` longtext COLLATE utf8_unicode_ci NOT NULL,
  `chemin_chapitre` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `chemin_video_chapitre` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `chemin_presentation_chapitre` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `contenu_chapitre` longtext COLLATE utf8_unicode_ci NOT NULL,
  `duree_chapitre` int(11) NOT NULL,
  `niveau_chapitre` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `introduction_chapitre` longtext COLLATE utf8_unicode_ci NOT NULL,
  `objectif_chapitre` longtext COLLATE utf8_unicode_ci NOT NULL,
  `date_ajout` datetime NOT NULL,
  PRIMARY KEY (`id_chapitre`),
  KEY `IDX_8C62B0257690A3FE` (`id_cours_chapitre`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=9 ;

--
-- Contenu de la table `chapitre`
--

INSERT INTO `chapitre` (`id_chapitre`, `id_cours_chapitre`, `titre_chapitre`, `description_chapitre`, `chemin_chapitre`, `chemin_video_chapitre`, `chemin_presentation_chapitre`, `contenu_chapitre`, `duree_chapitre`, `niveau_chapitre`, `introduction_chapitre`, `objectif_chapitre`, `date_ajout`) VALUES
(4, 9, 'SYMFONY2, UN FRAMEWORK PHP', '<p><strong>Symfony2 est un puissant framework</strong>&nbsp;qui va vous permettre de r&eacute;aliser des sites complexes rapidement, mais de fa&ccedil;on structur&eacute;e et avec un code clair et maintenable. En un mot : le paradis du d&eacute;veloppeur !</p>\r\n\r\n<p>Si vous ne ma&icirc;trisez pas ces trois points, je vous invite vraiment &agrave; les apprendre avant de commencer la lecture de ce cours. Symfony2 requiert ces bases, et si vous ne les avez pas, vous risquez de mettre plus de temps pour assimiler ce cours. C&#39;est comme acheter un A380 sans savoir piloter : c&#39;est joli mais vous n&#39;irez pas bien loin.</p>\r\n', '9SYMFONY2UNFRAMEWORKPHP.pdf', '9SYMFONY2UNFRAMEWORKPHP.MP4', '9gsbSymfony.pdf', '<h3 style="text-align:justify">Qu&#39;est-ce qu&#39;un framework ?</h3>\r\n\r\n<h4 style="text-align:justify">L&#39;objectif d&#39;un framework</h4>\r\n\r\n<p style="text-align: justify;">L&#39;objectif de ce chapitre n&#39;est pas de vous fournir toutes les cl&eacute;s pour&nbsp;<em>concevoir</em>&nbsp;un framework, mais suffisamment pour pouvoir en&nbsp;<em>utiliser</em>&nbsp;un. On exposera rapidement l&#39;int&eacute;r&ecirc;t, les avantages et les inconv&eacute;nients de l&#39;utilisation d&#39;un tel outil.</p>\r\n\r\n<h5 style="text-align:justify">D&eacute;finition</h5>\r\n\r\n<p style="text-align: justify;">Le mot &laquo;&nbsp;<em>framework</em>&nbsp;&raquo; provient de l&#39;anglais &laquo;&nbsp;<em>frame</em>&nbsp;&raquo; qui veut dire &laquo; cadre &raquo; en fran&ccedil;ais, et &laquo;&nbsp;<em>work</em>&nbsp;&raquo; qui signifie &laquo; travail &raquo;. Litt&eacute;ralement, c&#39;est donc un &laquo; cadre de travail &raquo;. Vous voil&agrave; bien avanc&eacute;s, hein ?&nbsp;<img alt=":p" src="https://openclassrooms.com/bundles/common/images/smiley/langue.png" />&nbsp;Concr&egrave;tement, c&#39;est un ensemble de composants qui sert &agrave; cr&eacute;er les fondations, l&#39;architecture et les grandes lignes d&#39;un logiciel. Il existe des centaines de frameworks couvrant la plupart des langages de programmation. Ils sont destin&eacute;s au d&eacute;veloppement de sites web ou bien &agrave; la conception de logiciels.</p>\r\n\r\n<p style="text-align: justify;">Un framework est une bo&icirc;te &agrave; outils con&ccedil;ue par un ou plusieurs d&eacute;veloppeurs &agrave; destination d&#39;autres d&eacute;veloppeurs. Contrairement &agrave; certains scripts tels que WordPress, Dotclear ou autres, un framework n&#39;est pas utilisable tel quel. Il n&#39;est pas fait pour &ecirc;tre utilis&eacute; par les utilisateurs finaux. Le d&eacute;veloppeur qui se sert d&#39;un framework a encore du boulot &agrave; fournir, d&#39;o&ugrave; ce cours !</p>\r\n\r\n<h5 style="text-align:justify">Objectif d&#39;un framework</h5>\r\n\r\n<p style="text-align: justify;">L&#39;objectif premier d&#39;un framework est d&#39;am&eacute;liorer la productivit&eacute; des d&eacute;veloppeurs qui l&#39;utilisent. Plut&ocirc;t sympa, non ? Souvent organis&eacute; en diff&eacute;rents composants, un framework offre la possibilit&eacute; au d&eacute;veloppeur final d&#39;utiliser tel ou tel composant pour lui faciliter le d&eacute;veloppement, et lui permet ainsi de se concentrer sur le plus important.</p>\r\n\r\n<p style="text-align: justify;">Prenons un exemple concret. Il existe dans Symfony2 un composant qui g&egrave;re les formulaires HTML : leur affichage, leur validation, etc. Le d&eacute;veloppeur qui l&#39;utilise se concentre sur l&#39;essentiel dans son application : chaque formulaire effectue une action, et c&#39;est cette action qui est importante, pas les formulaires. &Eacute;tendez ce principe &agrave; toute une application ou tout un site Internet, et vous comprenez l&#39;int&eacute;r&ecirc;t d&#39;un framework ! Autrement dit, le framework s&#39;occupe de la forme et permet au d&eacute;veloppeur de se concentrer sur le fond.</p>\r\n\r\n<h4 style="text-align:justify">Pesons le pour et le contre</h4>\r\n\r\n<p style="text-align: justify;">Comme tout bon d&eacute;veloppeur, lorsqu&#39;on veut utiliser un nouvel outil, on doit en peser le pour et le contre pour &ecirc;tre s&ucirc;r de faire le bon choix !</p>\r\n\r\n<h5 style="text-align:justify">Les pour</h5>\r\n\r\n<p style="text-align: justify;">L&#39;avantage premier est donc, on vient de le voir, le gain en productivit&eacute;. Mais il en existe bien d&#39;autres ! On peut les classer en plusieurs cat&eacute;gories : le code, le travail et la communaut&eacute;.</p>\r\n\r\n<p style="text-align: justify;">Tout d&#39;abord, un framework va vous aider &agrave; r&eacute;aliser un&nbsp;<strong>&laquo; bon code &raquo;</strong>. Par &laquo; bon code &raquo;, j&#39;entends qu&#39;il vous incite, de par sa propre architecture, &agrave; bien organiser votre code. Et un code bien organis&eacute; est un code facilement maintenable et &eacute;volutif ! De plus, un framework offre des briques pr&ecirc;tes &agrave; &ecirc;tre utilis&eacute;es (le composant&nbsp;<em>Formulaire</em>&nbsp;de Symfony2 par exemple), ce qui vous &eacute;vite de r&eacute;inventer la roue, et surtout qui vous permet d&#39;utiliser des briques puissantes et &eacute;prouv&eacute;es. En effet, ces briques sont d&eacute;velopp&eacute;es par des &eacute;quipes de d&eacute;veloppeurs chevronn&eacute;s, elles sont donc tr&egrave;s flexibles et tr&egrave;s robustes. Vous &eacute;conomisez ainsi des heures de d&eacute;veloppement !</p>\r\n\r\n<p style="text-align: justify;">Ensuite, un framework&nbsp;<strong>am&eacute;liore la fa&ccedil;on dont vous travaillez</strong>. En effet, dans le cas d&#39;un site Internet, vous travaillez souvent avec d&#39;autres d&eacute;veloppeurs PHP et un designer. Un framework vous aide doublement dans ce travail en &eacute;quipe. D&#39;une part, un framework utilise presque toujours l&#39;architecture MVC ; on en reparlera, mais sachez pour le moment que c&#39;est une fa&ccedil;on d&#39;organiser son code qui s&eacute;pare le code PHP du code HTML. Ainsi, votre designer peut travailler sur des fichiers diff&eacute;rents des v&ocirc;tres, fini les probl&egrave;mes d&#39;&eacute;dition simultan&eacute;e d&#39;un m&ecirc;me fichier ! D&#39;autre part, un framework a une structure et des conventions de code connues. Ainsi, vous pouvez facilement recruter un autre d&eacute;veloppeur : s&#39;il conna&icirc;t d&eacute;j&agrave; le framework en question, il s&#39;int&eacute;grera tr&egrave;s rapidement au projet.</p>\r\n\r\n<p style="text-align: justify;">Enfin, le dernier avantage est la&nbsp;<strong>communaut&eacute;</strong>&nbsp;soutenant chaque framework. C&#39;est elle qui fournit les tutoriaux ou les cours (comme celui que vous lisez !), de l&#39;aide sur les forums, et bien s&ucirc;r les mises &agrave; jour du framework. Ces mises &agrave; jour sont tr&egrave;s importantes : imaginez que vous codiez vous-m&ecirc;mes tout ce qui est connexion utilisateur, session, moteur de&nbsp;<em>templates</em>, etc. Comme il est impossible de coder sans bugs, vous devriez logiquement corriger chaque bug d&eacute;clar&eacute; sur votre code. Maintenant, imaginez que toutes les briques de votre site, qui ne sont pas forc&eacute;ment votre tasse de th&eacute;, soient fournies par le framework. &Agrave; chaque fois que vous ou les milliers d&#39;autres utilisateurs du framework trouverez un bug, les d&eacute;veloppeurs et la communaut&eacute; s&#39;occuperont de le corriger, et vous n&#39;aurez plus qu&#39;&agrave; suivre les mises &agrave; jour. Un vrai paradis !</p>\r\n\r\n<p style="text-align: justify;">Il existe plein d&#39;autres avantages que je ne vais pas vous d&eacute;tailler, mais un framework, c&#39;est aussi :</p>\r\n\r\n<ul>\r\n	<li>\r\n	<p style="text-align: justify;">une communaut&eacute; active qui utilise le framework et qui contribue en retour ;</p>\r\n	</li>\r\n	<li>\r\n	<p style="text-align: justify;">une documentation de qualit&eacute; et r&eacute;guli&egrave;rement mise &agrave; jour ;</p>\r\n	</li>\r\n	<li>\r\n	<p style="text-align: justify;">un code source maintenu par des d&eacute;veloppeurs attitr&eacute;s ;</p>\r\n	</li>\r\n	<li>\r\n	<p style="text-align: justify;">un code qui respecte les standards de programmation ;</p>\r\n	</li>\r\n	<li>\r\n	<p style="text-align: justify;">un support &agrave; long terme garanti et des mises &agrave; jour qui ne cassent pas la compatibilit&eacute; ;</p>\r\n	</li>\r\n	<li>\r\n	<p style="text-align: justify;">etc.</p>\r\n	</li>\r\n</ul>\r\n\r\n<h5 style="text-align:justify">Les contre</h5>\r\n\r\n<p style="text-align: justify;">Vous vous en doutez, avec autant d&#39;avantages il y a forc&eacute;ment des inconv&eacute;nients. Et bien, figurez-vous qu&#39;il n&#39;y en a pas tant que &ccedil;a !</p>\r\n\r\n<p style="text-align: justify;">S&#39;il ne fallait en citer qu&#39;un, cela serait &eacute;videmment la courbe d&#39;apprentissage qui est plus &eacute;lev&eacute;e. En effet, pour ma&icirc;triser un framework, il faut un temps d&#39;apprentissage non n&eacute;gligeable. Chaque brique qui compose un framework a sa complexit&eacute; propre qu&#39;il vous faudra appr&eacute;hender.</p>\r\n\r\n<p style="text-align: justify;">Notez &eacute;galement que pour les frameworks les plus r&eacute;cents, tels que Symfony2 justement, il faut &eacute;galement &ecirc;tre au courant des derni&egrave;res nouveaut&eacute;s de PHP. Je pense notamment &agrave;&nbsp;<a href="https://openclassrooms.com/informatique/cours/concevez-votre-site-web-avec-php-et-mysql/la-programmation-orientee-objet-6">la programmation orient&eacute;e objet (par Mathieu Nebra)</a>&nbsp;et aux&nbsp;<a href="https://openclassrooms.com/informatique/cours/les-espaces-de-noms-en-php">namespaces (par Victor Thuillier)</a>. De plus, conna&icirc;tre certaines bonnes pratiques telles que l&#39;architecture MVC est un plus.</p>\r\n\r\n<p style="text-align: justify;">Mais rien de tout cela ne doit vous effrayer ! Voyez l&#39;apprentissage d&#39;un framework comme un investissement : il y a un certain effort &agrave; fournir au d&eacute;but, mais les r&eacute;sultats se r&eacute;coltent ensuite sur le long terme !</p>\r\n\r\n<h4 style="text-align:justify">Alors, convaincus ?</h4>\r\n\r\n<p style="text-align: justify;">J&#39;esp&egrave;re vous avoir convaincus que les pour l&#39;emportent largement sur les contre. Si vous &ecirc;tes pr&ecirc;ts &agrave; relever le d&eacute;fi aujourd&#39;hui pour &ecirc;tre plus productifs demain, alors ce cours est fait pour vous !</p>\r\n\r\n<h3 style="text-align:justify">Qu&#39;est-ce que Symfony2 ?</h3>\r\n\r\n<h4 style="text-align:justify">Un framework</h4>\r\n\r\n<p style="text-align: justify;">Symfony2 est donc un framework PHP. Bien s&ucirc;r, il en existe d&#39;autres ; pour ne citer que les plus connus :&nbsp;<a href="http://framework.zend.com/">Zend Framework</a>,&nbsp;<a href="http://codeigniter.com/">CodeIgniter</a>,&nbsp;<a href="http://cakephp.org/">CakePHP</a>, etc. Le choix d&#39;un framework est assez personnel, et doit &ecirc;tre adapt&eacute; au projet engag&eacute;. Sans vouloir pr&ecirc;cher pour ma paroisse, Symfony2 est l&#39;un des plus flexibles et des plus puissants.</p>\r\n\r\n<h4 style="text-align:justify">Un framework populaire</h4>\r\n\r\n<p style="text-align: justify;">Symfony est tr&egrave;s populaire. C&#39;est un des frameworks les plus utilis&eacute;s dans le monde, notamment dans les entreprises. Il est utilis&eacute; par Dailymotion par exemple ! La premi&egrave;re version de Symfony est sortie en 2005 et est aujourd&#39;hui toujours tr&egrave;s utilis&eacute;e. Cela lui apporte un retour d&#39;exp&eacute;rience et une notori&eacute;t&eacute; exceptionnels. Aujourd&#39;hui, beaucoup d&#39;entreprises dans le domaine de l&#39;Internet (dont OpenClassrooms !) recrutent des d&eacute;veloppeurs capables de travailler sous ce framework. Ces d&eacute;veloppeurs pourront ainsi se greffer aux projets de l&#39;entreprise tr&egrave;s rapidement, car ils en conna&icirc;tront d&eacute;j&agrave; les grandes lignes. C&#39;est un atout si vous souhaitez travailler dans ce domaine.&nbsp;<img alt=";)" src="https://openclassrooms.com/bundles/common/images/smiley/clin.png" /></p>\r\n\r\n<p style="text-align: justify;">La deuxi&egrave;me version, que nous &eacute;tudierons dans ce cours, est sortie en ao&ucirc;t 2011. Elle est encore jeune, son d&eacute;veloppement a &eacute;t&eacute; fulgurant gr&acirc;ce &agrave; une communaut&eacute; de d&eacute;veloppeurs d&eacute;vou&eacute;s. Bien que diff&eacute;rente dans sa conception, cette deuxi&egrave;me version est plus rapide et plus souple que la premi&egrave;re. D&eacute;j&agrave;, beaucoup d&#39;entreprises s&#39;arrachent les comp&eacute;tences des d&eacute;veloppeurs Symfony2. Faites-en partie !</p>\r\n\r\n<h4 style="text-align:justify">Un framework populaire et fran&ccedil;ais</h4>\r\n\r\n<p style="text-align: justify;">Et oui, Symfony2, l&#39;un des meilleurs frameworks PHP au monde, est un framework fran&ccedil;ais ! Il est &eacute;dit&eacute; par la soci&eacute;t&eacute;&nbsp;<a href="http://sensiolabs.com/?utm_source=openclassroom&amp;utm_medium=accueil&amp;utm_campaign=PARTENARIATS">SensioLabs</a>, dont le cr&eacute;ateur est Fabien Potencier. Mais Symfony2 &eacute;tant&nbsp;<em>open source</em>, il a &eacute;galement &eacute;t&eacute; &eacute;crit par toute la communaut&eacute; : beaucoup de Fran&ccedil;ais, mais aussi des d&eacute;veloppeurs de tous horizons : Europe, &Eacute;tats-Unis, etc. C&#39;est gr&acirc;ce au talent de Fabien et &agrave; la g&eacute;n&eacute;rosit&eacute; de la communaut&eacute; que Symfony2 a vu le jour.</p>\r\n\r\n<h4 style="text-align:justify">Qu&#39;est-il possible de faire avec Symfony ?</h4>\r\n\r\n<p style="text-align: justify;">Avec Symfony, comme avec beaucoup de frameworks PHP, vous n&#39;&ecirc;tes limit&eacute;s que par votre imagination ! En effet, il est possible de tout faire : ce n&#39;est pas le framework qui vous posera des limites, celui-ci ne met en place qu&#39;un cadre de travail. Libre &agrave; vous d&#39;utiliser ce cadre comme bon vous semble ! Je vous ai parl&eacute; de<a href="http://www.dailymotion.com/fr">Dailymotion</a>, un site de partage de vid&eacute;os ; mais vous pouvez &eacute;galement cr&eacute;er un site e-commerce, comme je l&#39;ai fait avec&nbsp;<a href="https://www.caissin.fr/">Caissin</a>&nbsp;;&nbsp;ou encore un site plus complexe tel qu&#39;<a href="https://openclassrooms.com/">OpenClassrooms</a>&nbsp;qui tourne &eacute;galement sur Symfony2.</p>\r\n\r\n<p style="text-align: justify;">C&#39;est donc l&#39;une des forces de Symfony2 : il vous permettra de cr&eacute;er le site internet de vos r&ecirc;ves en vous fournissant tous les outils n&eacute;cessaires pour y arriver avec succ&egrave;s.</p>\r\n\r\n<h3 style="text-align:justify">T&eacute;l&eacute;charger Symfony2</h3>\r\n\r\n<h4 style="text-align:justify">V&eacute;rifier l&#39;installation de PHP en console</h4>\r\n\r\n<p style="text-align: justify;">Nous aurons parfois besoin d&#39;ex&eacute;cuter des commandes PHP via la console, pour g&eacute;n&eacute;rer du code ou g&eacute;rer la base de donn&eacute;es. Ce sont des commandes qui vont nous faire gagner du temps (toujours le m&ecirc;me objectif !), v&eacute;rifions donc que PHP est bien disponible en console. Rassurez-vous, je vous indiquerai toujours pas &agrave; pas comment les utiliser.</p>\r\n\r\n<p style="text-align: justify;">Si vous &ecirc;tes sous Linux ou Mac, vous ne devriez pas avoir de soucis, PHP est bien disponible en console. Si vous &ecirc;tes sous Windows, rien n&#39;est s&ucirc;r. Dans tous les cas, v&eacute;rifiez-le en ouvrant l&#39;invite de commandes pour Windows, ou le terminal pour Linux.</p>\r\n\r\n<h5 style="text-align:justify">Sous Windows</h5>\r\n\r\n<p style="text-align: justify;">Lancez l&#39;invite de commandes :</p>\r\n\r\n<p style="text-align: justify;"><code>Menu D&eacute;marrer &gt; Programmes &gt; Accessoires &gt; Invite de commandes</code>.</p>\r\n\r\n<p style="text-align: justify;">Une fen&ecirc;tre semblable &agrave; la figure suivante devrait appara&icirc;tre.</p>\r\n\r\n<p style="text-align: center;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<img alt="La console Windows" src="https://user.oc-static.com/files/415001_416000/415158.jpg" /></p>\r\n\r\n<p style="text-align: justify;">La console Windows</p>\r\n\r\n<p style="text-align: justify;">Puis ex&eacute;cutez la commande suivante :</p>\r\n\r\n<pre>\r\n<code class="language-bash">C:\\Users\\winzou&gt; php -v​\r\nPHP 5.5.12 (cli) (built: Apr 30 2014 11:20:55)​\r\nCopyright (c) 1997-2014 The PHP Group\r\nZend Engine v2.5.0, Copyright (c) 1998-2014 Zend Technologies\r\n with Zend OPcache v7.0.4-dev, Copyright (c) 1999-2014, by Zend Technologies</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n', 12, 'Normale', 'Vous savez déjà faire des sites Internet ? Vous maîtrisez votre code, mais n''êtes pas totalement satisfait ? Vous avez trop souvent l''impression de réinventer la roue ?', '<p>Ce cours est destin&eacute; aux d&eacute;butants de Symfony2. Vous n&#39;avez besoin d&#39;aucune notion sur les frameworks pour l&#39;aborder, car nous allons les d&eacute;couvrir ensemble, pas &agrave; pas. Cependant, il est fortement conseill&eacute; :</p>\r\n\r\n<ul>\r\n	<li>\r\n	<p>d&#39;avoir d&eacute;j&agrave; une bonne exp&eacute;rience de PHP (<a href="https://openclassrooms.com/informatique/cours/concevez-votre-site-web-avec-php-et-mysql">consultez le cours Concevez votre site web avec PHP et MySQL par Mathieu Nebra</a>) ;</p>\r\n	</li>\r\n	<li>\r\n	<p>de ma&icirc;triser les notions de base de la POO (<a href="https://openclassrooms.com/informatique/cours/concevez-votre-site-web-avec-php-et-mysql/la-programmation-orientee-objet-6">consultez le cours La programmation orient&eacute;e objet par Mathieu Nebra</a>) ;</p>\r\n	</li>\r\n	<li>\r\n	<p>d&#39;avoir &eacute;ventuellement des notions de namespace (<a href="https://openclassrooms.com/informatique/cours/les-espaces-de-noms-en-php">consultez le cours Les espaces de nom par Victor Thuillier</a>).</p>\r\n	</li>\r\n</ul>\r\n', '2016-03-14 23:08:45'),
(5, 9, 'Firas', 'Je Suis Malade de ce Pi DEV', '', '', '', '', 5, 'normale', 'introduction', 'objectif', '2016-05-10 00:00:00'),
(6, 10, 'Firas', 'Firas', 'Firas', 'Firas', 'Firas', 'Firas', 0, '', '', '', '0000-00-00 00:00:00'),
(7, 11, 'anas', 'anas²', 'anas', 'anas', 'anas', 'anas', 0, '', '', '', '0000-00-00 00:00:00'),
(8, 16, 'souhaib', 'souhaib', 'souhaib', 'souhaib', 'souhaib', 'v', 0, '', '', '', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `ancestors` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `depth` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `state` int(11) NOT NULL,
  `author_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_5BC96BF0E2904019` (`thread_id`),
  KEY `IDX_5BC96BF0F675F31B` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE IF NOT EXISTS `commentaire` (
  `id_commentaire` int(11) NOT NULL AUTO_INCREMENT,
  `id_apprenant_commentaire` int(11) DEFAULT NULL,
  `id_cours_commentaire` int(11) DEFAULT NULL,
  `texte_commentaire` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_commentaire`),
  KEY `IDX_67F068BCD2E32CA` (`id_apprenant_commentaire`),
  KEY `IDX_67F068BCD4706035` (`id_cours_commentaire`),
  KEY `id_apprenant_commentaire` (`id_apprenant_commentaire`,`id_cours_commentaire`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE IF NOT EXISTS `cours` (
  `id_cours` int(11) NOT NULL AUTO_INCREMENT,
  `id_discipline_cours` int(11) DEFAULT NULL,
  `id_formateur_cours` int(11) DEFAULT NULL,
  `titre_cours` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `description_cours` longtext COLLATE utf8_unicode_ci NOT NULL,
  `objectif_cours` longtext COLLATE utf8_unicode_ci NOT NULL,
  `prerequis_cours` longtext COLLATE utf8_unicode_ci NOT NULL,
  `duree_cours` int(11) NOT NULL,
  `etat_cours` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `video_cours` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `like_cours` int(11) DEFAULT NULL,
  `niveau_cours` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `introduction_cours` longtext COLLATE utf8_unicode_ci NOT NULL,
  `date_ajout` datetime NOT NULL,
  `nombre_visite` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_cours`),
  KEY `IDX_FDCA8C9C59276A05` (`id_discipline_cours`),
  KEY `IDX_FDCA8C9CC92EF92C` (`id_formateur_cours`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=17 ;

--
-- Contenu de la table `cours`
--

INSERT INTO `cours` (`id_cours`, `id_discipline_cours`, `id_formateur_cours`, `titre_cours`, `description_cours`, `objectif_cours`, `prerequis_cours`, `duree_cours`, `etat_cours`, `video_cours`, `like_cours`, `niveau_cours`, `introduction_cours`, `date_ajout`, `nombre_visite`) VALUES
(9, 9, 19, 'Développez votre site web avec le framework Symfony2', '<p>Ce cours vous permettra de prendre en main Symfony2, le framework PHP de r&eacute;f&eacute;rence. Pourquoi utiliser un framework ? Comment cr&eacute;er un nouveau projet de site web avec Symfony2, mettre en place les environnements de test et de production, concevoir les contr&ocirc;leurs, les templates, g&eacute;rer la traduction et communiquer avec une base de donn&eacute;es via Doctrine 2 ?</p>\r\n', '<p>Alexandre Bacco vous montrera tout au long de ce cours comment ce puissant framework, support&eacute; par une large communaut&eacute;, va vous faire gagner en efficacit&eacute;. Fabien Potencier, cr&eacute;ateur de Symfony2, introduira chacun des chapitres par une vid&eacute;o explicative des principaux points abord&eacute;s.</p>\r\n', '<p>Avoir une grande id&eacute;e sur le langage PHP...<img alt="laugh" src="http://127.0.0.1/MOOCV5/web/bundles/espritmooc/ckeditor/plugins/smiley/images/teeth_smile.png" style="height:23px; width:23px" title="laugh" /></p>\r\n\r\n<p>Ce cours, &eacute;crit par Alexandre Bacco, a &eacute;t&eacute; con&ccedil;u conjointement par SensioLabs, soci&eacute;t&eacute; &eacute;ditrice de Symfony2, et OpenClassrooms. Un certificat de r&eacute;ussite du cours sera d&eacute;livr&eacute; par SensioLabs et OpenClassrooms pour les &eacute;l&egrave;ves qui r&eacute;ussiront l&rsquo;ensemble des exercices.</p>\r\n', 40, 'accepter', '19Symfony2.MP4', 0, 'Difficile', 'Vous développez des sites web régulièrement et vous en avez assez de réinventer la roue ? Vous aimeriez utiliser les bonnes pratiques de développement PHP pour concevoir des sites web de qualité professionnelle ?', '2016-03-14 22:47:38', 25),
(10, 1, 19, 'Apprenez à programmer en Java', '<p>Bonjour &agrave; tous !&nbsp;<img alt=":D" src="https://openclassrooms.com/bundles/common/images/smiley/heureux.png" /></p>\r\n\r\n<p>Bienvenue dans mon cours de programmation en Java. C&#39;est un langage tr&egrave;s utilis&eacute;, notamment par un grand nombre de programmeurs professionnels, ce qui en fait un langage incontournable actuellement.</p>\r\n\r\n<p>Voici les caract&eacute;ristiques de Java en quelques mots :</p>\r\n\r\n<p>Java est un langage de programmation moderne d&eacute;velopp&eacute; par&nbsp;<strong>Sun Microsystems</strong>&nbsp;(aujourd&#39;hui rachet&eacute; par<strong>Oracle</strong>). Il ne faut surtout pas le confondre avec JavaScript (langage de scripts utilis&eacute; principalement sur les sites web), car Java n&#39;a rien &agrave; voir.<br />\r\nUne de ses plus grandes forces est son excellente portabilit&eacute; : une fois votre programme cr&eacute;&eacute;, il fonctionnera automatiquement sous Windows, Mac, Linux, etc.</p>\r\n\r\n<p>On peut faire de nombreuses sortes de programmes avec Java :</p>\r\n\r\n<ul>\r\n	<li style="text-align: center;">\r\n	<p>des applications, sous forme de fen&ecirc;tre ou de console ;</p>\r\n	<img alt="Exemple de programme développé en Java - préciser type" src="https://user.oc-static.com/thb/401001_402000/401712.jpg" />\r\n	<p>Exemple d&#39;application Java sous forme de fen&ecirc;tre (extraite de ce cours)</p>\r\n	</li>\r\n	<li style="text-align: center;">\r\n	<p>des applets, qui sont des programmes Java incorpor&eacute;s &agrave; des pages web ;</p>\r\n	<img alt="Exemple d''applet Java" src="https://s3-eu-west-1.amazonaws.com/sdz-upload/prod/upload/WWJ_Applet_04_400.jpg" />\r\n	<p>Exemple d&#39;applet Java</p>\r\n	</li>\r\n	<li style="text-align: center;">\r\n	<p>des applications pour appareils mobiles, avec J2ME ;</p>\r\n	<img alt="Exemple d''application mobile Java" src="https://s3-eu-west-1.amazonaws.com/sdz-upload/prod/upload/Snails2.21.png" />\r\n	<p>Exemple d&#39;application mobile Java</p>\r\n	</li>\r\n	<li>\r\n	<p>et bien d&#39;autres ! J2EE, JMF, J3D pour la 3D...</p>\r\n	</li>\r\n</ul>\r\n\r\n<p>Comme vous le voyez, Java permet de r&eacute;aliser une tr&egrave;s grande quantit&eacute; d&#39;applications diff&eacute;rentes ! Mais... comment apprendre un langage si vaste qui offre autant de possibilit&eacute;s ?&nbsp;<img alt="o_O" src="https://openclassrooms.com/bundles/common/images/smiley/blink.gif" /></p>\r\n\r\n<p>Heureusement, ce cours est l&agrave; pour tout vous apprendre de Java &agrave; partir de z&eacute;ro&nbsp;<img alt=":)" src="https://openclassrooms.com/bundles/common/images/smiley/smile.png" />&nbsp;.</p>\r\n', '<p>Je tiens &agrave; faire une d&eacute;dicace sp&eacute;ciale &agrave; ptipilou, zCorrecteur &eacute;m&eacute;rite, sans qui ce cours&nbsp;n&#39;aurait pas vu le jour !<br />\r\nUn grand merci pour ton travail et ton soutien !&nbsp;<img alt=":)" src="https://openclassrooms.com/bundles/common/images/smiley/smile.png" /></p>\r\n', '<p>Je tiens &agrave; faire une d&eacute;dicace sp&eacute;ciale &agrave; ptipilou, zCorrecteur &eacute;m&eacute;rite, sans qui ce cours&nbsp;n&#39;aurait pas vu le jour !<br />\r\nUn grand merci pour ton travail et ton soutien !&nbsp;<img alt=":)" src="https://openclassrooms.com/bundles/common/images/smiley/smile.png" /></p>\r\n', 40, 'en attente', '19videoIntroduction.mp4', 0, 'Difficile', 'Introduction du cours\r\nBonjour à tous ! :D\r\n\r\nBienvenue dans mon cours de programmation en Java. C''est un langage très utilisé, notamment par un grand nombre de programmeurs professionnels, ce qui en fait un langage incontournable actuellement.', '2016-03-15 16:42:26', 39),
(11, 12, 20, 'Simplifiez vos développements JavaScript avec jQuery', '<p style="text-align: justify;">Avec ce cours, je vous propose de d&eacute;couvrir les multiples facettes du&nbsp;<em>framework</em>&nbsp;jQuery. De la s&eacute;lection d&#39;&eacute;l&eacute;ments &agrave; la manipulation du DOM, en passant par l&#39;animation, les requ&ecirc;tes AJAX, l&#39;utilisation et la cr&eacute;ation de plugins, la cr&eacute;ation de jeux et bien d&#39;autres choses encore !</p>\r\n\r\n<p style="text-align: justify;">N&#39;ayez crainte, votre apprentissage se fera en douceur et de tr&egrave;s nombreux exemples de code document&eacute;s viendront consolider vos connaissances. Au fil des pages, votre approche deviendra de plus en plus naturelle et les nouveaux chapitres ne feront qu&#39;apporter une pierre de plus &agrave; l&#39;&eacute;difice, sans en &eacute;branler les fondations. Si vous le souhaitez, vous pouvez am&eacute;liorer vos connaissances en JavaScript en consultant&nbsp;<a href="http://www.siteduzero.com/tutoriel-3-309961-dynamisez-vos-sites-web-avec-javascript.html">ce cours</a>.</p>\r\n\r\n<p style="text-align: justify;">Si vous lisez ces lignes, c&#39;est parce que vous avez d&eacute;cid&eacute; d&#39;aller plus loin dans vos d&eacute;veloppements Web. En portant votre choix sur jQuery, je peux vous certifier que vous avez fait le bon choix. Si aujourd&#39;hui de plus en plus d&#39;entreprises l&#39;utilisent pour leur site Web, ce n&#39;est pas par hasard. Tournez vite les pages et devenez, vous aussi, un inconditionnel de jQuery. Et surtout, amusez-vous bien !</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p style="text-align: center;">&nbsp;</p>\r\n\r\n<p style="text-align: center;"><img alt="Le logo de jQuery" src="https://user.oc-static.com/files/385001_386000/385862.png" /></p>\r\n\r\n<p style="text-align: center;">Le logo de jQuery</p>\r\n\r\n<p>&nbsp;</p>\r\n', '<p>Avec ce cours, je vous propose de d&eacute;couvrir les multiples facettes du&nbsp;<em>framework</em>&nbsp;jQuery. De la s&eacute;lection d&#39;&eacute;l&eacute;ments &agrave; la manipulation du DOM, en passant par l&#39;animation, les requ&ecirc;tes AJAX, l&#39;utilisation et la cr&eacute;ation de plugins, la cr&eacute;ation de jeux et bien d&#39;autres choses encore !</p>\r\n', '<p>Avec ce cours, je vous propose de d&eacute;couvrir les multiples facettes du&nbsp;<em>framework</em>&nbsp;jQuery. De la s&eacute;lection d&#39;&eacute;l&eacute;ments &agrave; la manipulation du DOM, en passant par l&#39;animation, les requ&ecirc;tes AJAX, l&#39;utilisation et la cr&eacute;ation de plugins, la cr&eacute;ation de jeux et bien d&#39;autres choses encore !</p>\r\n', 28, 'en attente', '20jQueryT.MP4', 0, 'Normal', 'Si vous avez déjà programmé en JavaScript, vous savez que ce langage est puissant, mais aussi « verbeux » et souvent assez complexe à mettre en œuvre. Si vous voulez accéder à toute la puissance de JavaScript en utilisant des instructions simples, logiques, faciles à comprendre et à maintenir, jQuery est vraiment fait pour vous !\r\n', '2016-03-16 13:02:08', 8),
(16, 3, 19, 'test', '<html dir="ltr"><head></head><body contenteditable="true"><p><font face="Segoe UI">fd</font></p></body></html>', '<html dir="ltr"><head></head><body contenteditable="true"><p><font face="Segoe UI">fdf</font></p></body></html>', '<html dir="ltr"><head></head><body contenteditable="true"><p><font face="Segoe UI">fdfd</font></p></body></html>', 5, 'en attente', 'test.mp4', 0, 'Normale', '<html dir="ltr"><head></head><body contenteditable="true"><p><font face="Segoe UI">fdffdf</font></p></body></html>', '2016-04-07 00:00:00', 0);

-- --------------------------------------------------------

--
-- Structure de la table `discipline`
--

CREATE TABLE IF NOT EXISTS `discipline` (
  `id_discipline` int(11) NOT NULL AUTO_INCREMENT,
  `nom_discipline` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `logo` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `nombre_cours` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_discipline`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=13 ;

--
-- Contenu de la table `discipline`
--

INSERT INTO `discipline` (`id_discipline`, `nom_discipline`, `logo`, `nombre_cours`) VALUES
(1, 'Orienté Object', 'java.png', 1),
(2, 'Android', 'android.png', NULL),
(3, 'IOS', 'ios.png', NULL),
(4, 'Windows Phone', 'windowsPhone.png', NULL),
(5, 'BlackBerry OS', 'blackBerry.png', NULL),
(6, 'Share Point', 'sharepoint.png', NULL),
(7, 'Java Me', 'j2me.png', NULL),
(8, 'BootStrap', 'bootstrap.png', NULL),
(9, 'PHP', 'php.png', 1),
(10, 'C#', 'csharp.png', NULL),
(11, 'HTML5', 'html5.png', NULL),
(12, 'Java Script', 'javascript.png', 1);

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

CREATE TABLE IF NOT EXISTS `entreprise` (
  `id_entreprise` int(11) NOT NULL AUTO_INCREMENT,
  `nom_entreprise` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `description_entreprise` varchar(350) COLLATE utf8_unicode_ci NOT NULL,
  `attestation_entreprise` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `date_creation_entreprise` date NOT NULL,
  `responsable_entreprise` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `etat_entreprise` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `adresse_entreprise` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tel_entreprise` int(11) NOT NULL,
  PRIMARY KEY (`id_entreprise`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `forum`
--

CREATE TABLE IF NOT EXISTS `forum` (
  `id_forum` int(11) NOT NULL AUTO_INCREMENT,
  `id_discipline_forum` int(11) DEFAULT NULL,
  `nom_forum` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `nombre_sujet` int(11) DEFAULT NULL,
  `date_creation` datetime NOT NULL,
  `last_sujet` int(11) DEFAULT NULL,
  `nombre_vu` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_forum`),
  KEY `IDX_44EA91C921C65854` (`id_discipline_forum`),
  KEY `IDX_44EA91C94C586F86` (`last_sujet`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=13 ;

--
-- Contenu de la table `forum`
--

INSERT INTO `forum` (`id_forum`, `id_discipline_forum`, `nom_forum`, `nombre_sujet`, `date_creation`, `last_sujet`, `nombre_vu`) VALUES
(1, 1, 'Programmation Orienté Objet', 0, '2014-11-07 00:00:00', NULL, 5491),
(2, 2, 'Android Mobile', 2, '2016-11-30 00:00:00', NULL, 5662),
(3, 10, 'Csharp/C#', 2, '2015-03-30 00:00:00', 11, 2249),
(4, 3, 'IOS/Swift', 0, '2015-07-13 00:00:00', NULL, 7986),
(5, 4, 'Windows Phone', 0, '2015-07-13 00:00:00', NULL, 6985),
(6, 5, 'BlackBerry OS', 0, '2015-07-13 00:00:00', NULL, 7986),
(7, 6, 'Share Point', 0, '2015-07-13 00:00:00', NULL, 5632),
(8, 7, 'Java ME / J2ME', 0, '2015-07-13 00:00:00', NULL, 7798),
(9, 8, 'BootStrap/CSS', 1, '2015-07-13 00:00:00', NULL, 8018),
(10, 9, 'PHP', 3, '2015-07-13 00:00:00', NULL, 7675),
(11, 11, 'HTML/HTML5', 0, '2015-07-13 00:00:00', NULL, 2210),
(12, 12, 'Java Script', 0, '2015-07-13 00:00:00', NULL, 7643);

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

CREATE TABLE IF NOT EXISTS `fos_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `locked` tinyint(1) NOT NULL,
  `expired` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL,
  `confirmation_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `credentials_expired` tinyint(1) NOT NULL,
  `credentials_expire_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `information_entreprise`
--

CREATE TABLE IF NOT EXISTS `information_entreprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entreprise_id` int(11) DEFAULT NULL,
  `specialite` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `site_web` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `abreviation` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `attestation` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `filename` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresse` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `nationnalite` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `numTel` int(11) NOT NULL,
  `matriculeFiscal` int(11) NOT NULL,
  `raison_inscription` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `date_creation` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_414C97B9A4AEAFEA` (`entreprise_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Contenu de la table `information_entreprise`
--

INSERT INTO `information_entreprise` (`id`, `entreprise_id`, `specialite`, `site_web`, `abreviation`, `attestation`, `filename`, `description`, `type`, `adresse`, `nationnalite`, `numTel`, `matriculeFiscal`, `raison_inscription`, `date_creation`) VALUES
(1, 53, 'entreprise1', 'entreprise1', 'ent2', 'C:\\wamp\\www\\MOOCV5\\web\\uploads\\attestation\\attestationentreprise1.pdf', 'attestationentreprise1.pdf', 'entreprise1', 'PME', 'entreprise1', 'France', 20100242, 1200, 'entreprise1', '2016-03-05');

-- --------------------------------------------------------

--
-- Structure de la table `information_formateur`
--

CREATE TABLE IF NOT EXISTS `information_formateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formateur_id` int(11) DEFAULT NULL,
  `specialite` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `cv` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `filename` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `googlePlus` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `siteWeb` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `aPropos` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `biographie` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `miniBiographie` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_53FA7BCD155D8F51` (`formateur_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- Contenu de la table `information_formateur`
--

INSERT INTO `information_formateur` (`id`, `formateur_id`, `specialite`, `cv`, `filename`, `googlePlus`, `siteWeb`, `sex`, `aPropos`, `biographie`, `miniBiographie`) VALUES
(8, 19, 'Android', 'C:\\wamp\\www\\MOOCV5\\app/../web/uploads/cv/cvSouhaib.pdf', 'cvSouhaib.pdf', 'http://www.googlePlusSouhaib.com', 'https://www.facebook.com/Souhaib882', 'Homme', '<p>Anniversaire : Le 03 octobre(24&nbsp;ans)</p>\r\n\r\n<p>Passionn&eacute; de d&eacute;veloppement web, Alexandre participe &agrave; la cr&eacute;ation de la version 3 d&#39;OpenClassrooms durant ses &eacute;tudes. Dipl&ocirc;m&eacute; de l&#39;&Eacute;cole Centrale de Lyon, une &eacute;cole d&#39;ing&eacute;nieur g&eacute;n&eacute;raliste, il tombe sous le charme du framework Symfony2 avant m&ecirc;me sa sortie et d&eacute;cide de partager ses connaissances en r&eacute;digeant un cours sur OpenClassrooms.</p>\r\n', '', '<p>Ing&eacute;nieur Centralien et Entrepreneur...</p>\r\n'),
(9, 43, 'Android', 'C:\\wamp\\www\\MOOCV4\\web\\uploads\\Salim.pdf', 'hello', 'salim+', 'salim.com', 'Homme', 'ss', 'salim', 'ss'),
(10, 44, 'Android', 'C:\\wamp\\www\\MOOCV5\\web\\uploads\\cv\\saleh.pdf', 'saleh.pdf', 'saleh+', 'saleh.com', 'Homme', 'saleh', 'saleh	', 'saleh	sa');

-- --------------------------------------------------------

--
-- Structure de la table `invitation_comite`
--

CREATE TABLE IF NOT EXISTS `invitation_comite` (
  `id_invitation_comite` int(11) NOT NULL AUTO_INCREMENT,
  `id_formateur_invitation_comite` int(11) DEFAULT NULL,
  `etat_invitation_comite` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_invitation_comite`),
  KEY `IDX_90041155264B8AD5` (`id_formateur_invitation_comite`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `invitation_entreprise_formateur`
--

CREATE TABLE IF NOT EXISTS `invitation_entreprise_formateur` (
  `id_invitation_entreprise_formateur` int(11) NOT NULL AUTO_INCREMENT,
  `id_formateur` int(11) DEFAULT NULL,
  `id_entreprise` int(11) DEFAULT NULL,
  `etat_invitation` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_invitation_entreprise_formateur`),
  KEY `IDX_6AD34A936D43C268` (`id_formateur`),
  KEY `IDX_6AD34A93A8937AB7` (`id_entreprise`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `invitation_formateur_entreprise`
--

CREATE TABLE IF NOT EXISTS `invitation_formateur_entreprise` (
  `id_invitation_formateur__entreprise` int(11) NOT NULL AUTO_INCREMENT,
  `id_formateur` int(11) DEFAULT NULL,
  `id_entreprise` int(11) DEFAULT NULL,
  `etat_invitation` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_invitation_formateur__entreprise`),
  KEY `IDX_422B4B806D43C268` (`id_formateur`),
  KEY `IDX_422B4B80A8937AB7` (`id_entreprise`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `id_message` int(11) NOT NULL AUTO_INCREMENT,
  `id_utilisateur_message` int(11) DEFAULT NULL,
  `id_sujet_message` int(11) DEFAULT NULL,
  `contenu_message` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `titre_message` longtext COLLATE utf8_unicode_ci NOT NULL,
  `date_publication_message` datetime NOT NULL,
  `date_modification_message` datetime DEFAULT NULL,
  PRIMARY KEY (`id_message`),
  KEY `IDX_790009E38A5C5B26` (`id_utilisateur_message`),
  KEY `IDX_790009E385A6EC73` (`id_sujet_message`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=28 ;

--
-- Contenu de la table `message`
--

INSERT INTO `message` (`id_message`, `id_utilisateur_message`, `id_sujet_message`, `contenu_message`, `titre_message`, `date_publication_message`, `date_modification_message`) VALUES
(1, 19, 3, '<p>Salut Alexandre,</p>\r\n\r\n<p>Il reste moins de 4 jours pour commencer le mooc&nbsp;<strong>Symfony2</strong>&nbsp;et je veux savoir le bagage n&eacute;cessaire pour suivre le cours pour que je puisse rattraper dans ces jours restants.</p>\r\n\r\n<p>Merci</p>\r\n', 'Salut Alexandre', '2016-03-15 10:38:20', NULL),
(2, 19, 3, '<p>Bonjour</p>\r\n\r\n<p>Je ne sais pas si c&#39;est intentionnel mais pour l&#39;instant les quizzes indiquent que le cours est toujours en pr&eacute;inscription.</p>\r\n', 'Bonjour..', '2016-03-15 10:39:41', NULL),
(4, 20, 3, '<p>Oui, la balise&nbsp;</p>\r\n\r\n<table>\r\n	<tbody>\r\n		<tr>\r\n			<td>\r\n			<p>1</p>\r\n			</td>\r\n			<td>\r\n			<p><code>&lt;meta name=</code><code>&quot;generator&quot;</code>&nbsp;<code>content=</code><code>&quot;WordPress &lt;?php bloginfo(&#39;version&#39;); ?&gt;&quot;</code>&nbsp;<code>/&gt;</code></p>\r\n			</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n\r\n<p>n&#39;est finalement pas souvent pr&eacute;sente d&#39;apr&egrave;s ce que j&#39;ai pu remarquer sur pas mal de mes sites sous WP.</p>\r\n\r\n<p>De toute fa&ccedil;on, ce n&#39;est pas tr&egrave;s grave de la garder pour l&#39;instant&nbsp;<img alt=";)" src="https://openclassrooms.com/bundles/tinymce/vendor/tiny_mce/plugins/emotions/img/clin.png" title=";)" /></p>\r\n', 'MOOC Créez votre premier site WordPress', '2016-03-15 11:04:42', NULL),
(7, 19, 2, '<p>Bonjour</p>\r\n\r\n<p>Je ne sais pas si c&#39;est intentionnel mais pour l&#39;instant les quizzes indiquent que le cours est toujours en pr&eacute;inscription.</p>\r\n', 'Bonjour', '2016-03-15 13:20:36', NULL),
(8, 19, 3, '<p>franckhuby</p>\r\n\r\n<p>Lors de l&#39;installation par composer, une ligne demande si l&#39;on souhaite la g&eacute;n&eacute;ration du Bundle Acme, du moins lors de mes diff&eacute;rente installations.</p>\r\n\r\n<p>&gt;ruffiem</p>\r\n\r\n<p>Le script t&#39;indique qu&#39;il est juste incapable de connaitre l&#39;heure de ton syst&egrave;me, tu dois donc soit param&eacute;trer ton syst&egrave;me (linux commande date), soit utiliser la config ou&nbsp; la m&eacute;thode&nbsp; date_timezone (comment je ne sais pas)</p>\r\n\r\n<p>Bug ?</p>\r\n\r\n<p>Sur le Quizz de la partie 2 la correction ne correspond pas au texte d&#39;explication de correction.</p>\r\n', 'Salut', '2016-03-15 13:28:49', NULL),
(10, 20, 3, '<p>Oui, la balise&nbsp;</p>\r\n\r\n<table>\r\n	<tbody>\r\n		<tr>\r\n			<td>\r\n			<p>1</p>\r\n			</td>\r\n			<td>\r\n			<p><code>&lt;meta name=</code><code>&quot;generator&quot;</code>&nbsp;<code>content=</code><code>&quot;WordPress &lt;?php bloginfo(&#39;version&#39;); ?&gt;&quot;</code>&nbsp;<code>/&gt;</code></p>\r\n			</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n\r\n<p>n&#39;est finalement pas souvent pr&eacute;sente d&#39;apr&egrave;s ce que j&#39;ai pu remarquer sur pas mal de mes sites sous WP.</p>\r\n\r\n<p>De toute fa&ccedil;on, ce n&#39;est pas tr&egrave;s grave de la garder pour l&#39;instant&nbsp;<img alt=";)" src="https://openclassrooms.com/bundles/tinymce/vendor/tiny_mce/plugins/emotions/img/clin.png" title=";)" /></p>\r\n', 'les balises', '2016-03-15 14:00:57', NULL),
(16, 20, 5, '<p>aaa</p>\r\n', 'aaa', '2016-03-15 23:57:50', NULL),
(27, 20, 10, '<p>Si je peux me permettre:&nbsp;<br />\r\n<br />\r\n<a href="http://msdn.microsoft.com/fr-fr/netframework/dd742324.aspx">Lien pour des webcasts sur le C# et Mysql</a>&nbsp;car c&#39;est une question r&eacute;currente sur le forum.</p>\r\n', 'Si je peux me permettre', '2016-03-16 18:01:27', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

CREATE TABLE IF NOT EXISTS `notification` (
  `id_notification` int(11) NOT NULL AUTO_INCREMENT,
  `id_utilisateur_notification` int(11) DEFAULT NULL,
  `id_sujet_notification` int(11) DEFAULT NULL,
  `id_proprietaire_notification` int(11) DEFAULT NULL,
  `date_notification` datetime NOT NULL,
  `etat_notification` int(11) NOT NULL,
  PRIMARY KEY (`id_notification`),
  KEY `IDX_A765AD32BDA3681A` (`id_utilisateur_notification`),
  KEY `IDX_A765AD32F23EB44B` (`id_sujet_notification`),
  KEY `IDX_A765AD322C55FF01` (`id_proprietaire_notification`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=29 ;

--
-- Contenu de la table `notification`
--

INSERT INTO `notification` (`id_notification`, `id_utilisateur_notification`, `id_sujet_notification`, `id_proprietaire_notification`, `date_notification`, `etat_notification`) VALUES
(1, 19, 3, 19, '2016-03-15 10:38:20', 1),
(2, 19, 3, 19, '2016-03-15 10:39:41', 1),
(3, 19, 3, 19, '2016-03-15 10:43:19', 1),
(4, 20, 3, 19, '2016-03-15 11:04:42', 1),
(5, 19, 4, 20, '2016-03-15 12:33:41', 1),
(6, 19, 2, 19, '2016-03-15 13:17:50', 1),
(7, 19, 2, 19, '2016-03-15 13:20:36', 1),
(8, 19, 3, 19, '2016-03-15 13:28:49', 1),
(9, 19, 3, 19, '2016-03-15 13:59:28', 1),
(10, 20, 3, 19, '2016-03-15 14:00:57', 1),
(11, 20, 5, 19, '2016-03-15 23:29:44', 1),
(12, 20, 4, 20, '2016-03-15 23:33:17', 1),
(14, 20, 5, 19, '2016-03-15 23:39:29', 1),
(15, 20, 5, 19, '2016-03-15 23:55:37', 1),
(16, 20, 5, 19, '2016-03-15 23:57:50', 1),
(17, 19, 5, 19, '2016-03-15 23:58:43', 1),
(18, 19, 5, 19, '2016-03-15 23:59:24', 1),
(19, 19, 5, 19, '2016-03-16 00:00:54', 1),
(20, 20, 4, 20, '2016-03-16 00:12:23', 1),
(21, 19, 4, 20, '2016-03-16 00:14:40', 1),
(22, 20, 2, 19, '2016-03-16 00:20:22', 0),
(23, 20, 4, 20, '2016-03-16 00:22:44', 1),
(26, 20, 10, 20, '2016-03-16 17:53:53', 1),
(27, 20, 10, 20, '2016-03-16 18:01:27', 1),
(28, 20, 10, 20, '2016-03-16 18:03:32', 1);

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

CREATE TABLE IF NOT EXISTS `pays` (
  `PAYSID` int(11) NOT NULL AUTO_INCREMENT,
  `PAYSNAME` varchar(80) NOT NULL,
  `PAYSCODE` char(2) NOT NULL,
  PRIMARY KEY (`PAYSID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=237 ;

--
-- Contenu de la table `pays`
--

INSERT INTO `pays` (`PAYSID`, `PAYSNAME`, `PAYSCODE`) VALUES
(0, 'France', 'fr'),
(1, 'Afghanistan', 'af'),
(2, 'Afrique du sud', 'za'),
(3, 'Albanie', 'al'),
(4, 'Algérie', 'dz'),
(5, 'Allemagne', 'de'),
(6, 'Arabie saoudite', 'sa'),
(7, 'Argentine', 'ar'),
(8, 'Australie', 'au'),
(9, 'Autriche', 'at'),
(10, 'Belgique', 'be'),
(11, 'Brésil', 'br'),
(12, 'Bulgarie', 'bg'),
(13, 'Canada', 'ca'),
(14, 'Chili', 'cl'),
(15, 'Chine (Rép. pop.)', 'cn'),
(16, 'Colombie', 'co'),
(17, 'Corée, Sud', 'kr'),
(18, 'Costa Rica', 'cr'),
(19, 'Croatie', 'hr'),
(20, 'Danemark', 'dk'),
(21, 'Égypte', 'eg'),
(22, 'Émirats arabes unis', 'ae'),
(23, 'Équateur', 'ec'),
(24, 'États-Unis', 'us'),
(25, 'El Salvador', 'sv'),
(26, 'Espagne', 'es'),
(27, 'Finlande', 'fi'),
(28, 'Grèce', 'gr'),
(29, 'Hong Kong', 'hk'),
(30, 'Hongrie', 'hu'),
(31, 'Inde', 'in'),
(32, 'Indonésie', 'id'),
(33, 'Irlande', 'ie'),
(34, 'palestine', 'pa'),
(35, 'Italie', 'it'),
(36, 'Japon', 'jp'),
(37, 'Jordanie', 'jo'),
(38, 'Liban', 'lb'),
(39, 'Malaisie', 'my'),
(40, 'Maroc', 'ma'),
(41, 'Mexique', 'mx'),
(42, 'Norvège', 'no'),
(43, 'Nouvelle-Zélande', 'nz'),
(44, 'Pérou', 'pe'),
(45, 'Pakistan', 'pk'),
(46, 'Pays-Bas', 'nl'),
(47, 'Philippines', 'ph'),
(48, 'Pologne', 'pl'),
(49, 'Porto Rico', 'pr'),
(50, 'Portugal', 'pt'),
(51, 'République tchèque', 'cz'),
(52, 'Roumanie', 'ro'),
(53, 'Royaume-Uni', 'uk'),
(54, 'Russie', 'ru'),
(55, 'Singapour', 'sg'),
(56, 'Suède', 'se'),
(57, 'Suisse', 'ch'),
(58, 'Taiwan', 'tw'),
(59, 'Thailande', 'th'),
(60, 'Turquie', 'tr'),
(61, 'Ukraine', 'ua'),
(62, 'Venezuela', 've'),
(63, 'Yougoslavie', 'yu'),
(64, 'Samoa', 'as'),
(65, 'Andorre', 'ad'),
(66, 'Angola', 'ao'),
(67, 'Anguilla', 'ai'),
(68, 'Antarctique', 'aq'),
(69, 'Antigua et Barbuda', 'ag'),
(70, 'Arménie', 'am'),
(71, 'Aruba', 'aw'),
(72, 'Azerbaïdjan', 'az'),
(73, 'Bahamas', 'bs'),
(74, 'Bahrain', 'bh'),
(75, 'Bangladesh', 'bd'),
(76, 'Biélorussie', 'by'),
(77, 'Belize', 'bz'),
(78, 'Benin', 'bj'),
(79, 'Bermudes (Les)', 'bm'),
(80, 'Bhoutan', 'bt'),
(81, 'Bolivie', 'bo'),
(82, 'Bosnie-Herzégovine', 'ba'),
(83, 'Botswana', 'bw'),
(84, 'Bouvet (Îles)', 'bv'),
(85, 'Territoire britannique de l''océan Indien', 'io'),
(86, 'Vierges britanniques (Îles)', 'vg'),
(87, 'Brunei', 'bn'),
(88, 'Burkina Faso', 'bf'),
(89, 'Burundi', 'bi'),
(90, 'Cambodge', 'kh'),
(91, 'Cameroun', 'cm'),
(92, 'Cap Vert', 'cv'),
(93, 'Cayman (Îles)', 'ky'),
(94, 'République centrafricaine', 'cf'),
(95, 'Tchad', 'td'),
(96, 'Christmas (Île)', 'cx'),
(97, 'Cocos (Îles)', 'cc'),
(98, 'Comores', 'km'),
(99, 'Rép. Dém. du Congo', 'cg'),
(100, 'Cook (Îles)', 'ck'),
(101, 'Cuba', 'cu'),
(102, 'Chypre', 'cy'),
(103, 'Djibouti', 'dj'),
(104, 'Dominique', 'dm'),
(105, 'République Dominicaine', 'do'),
(106, 'Timor', 'tp'),
(107, 'Guinée Equatoriale', 'gq'),
(108, 'Érythrée', 'er'),
(109, 'Estonie', 'ee'),
(110, 'Ethiopie', 'et'),
(111, 'Falkland (Île)', 'fk'),
(112, 'Féroé (Îles)', 'fo'),
(113, 'Fidji (République des)', 'fj'),
(114, 'Guyane française', 'gf'),
(115, 'Polynésie française', 'pf'),
(116, 'Territoires français du sud', 'tf'),
(117, 'Gabon', 'ga'),
(118, 'Gambie', 'gm'),
(119, 'Géorgie', 'ge'),
(120, 'Ghana', 'gh'),
(121, 'Gibraltar', 'gi'),
(122, 'Groenland', 'gl'),
(123, 'Grenade', 'gd'),
(124, 'Guadeloupe', 'gp'),
(125, 'Guam', 'gu'),
(126, 'Guatemala', 'gt'),
(127, 'Guinée', 'gn'),
(128, 'Guinée-Bissau', 'gw'),
(129, 'Guyane', 'gy'),
(130, 'Haïti', 'ht'),
(131, 'Heard et McDonald (Îles)', 'hm'),
(132, 'Honduras', 'hn'),
(133, 'Islande', 'is'),
(134, 'Iran', 'ir'),
(135, 'Irak', 'iq'),
(136, 'Côte d''Ivoire', 'ci'),
(137, 'Jamaïque', 'jm'),
(138, 'Kazakhstan', 'kz'),
(139, 'Kenya', 'ke'),
(140, 'Kiribati', 'ki'),
(141, 'Corée du Nord', 'kp'),
(142, 'Koweit', 'kw'),
(143, 'Kirghizistan', 'kg'),
(144, 'Laos', 'la'),
(145, 'Lettonie', 'lv'),
(146, 'Lesotho', 'ls'),
(147, 'Libéria', 'lr'),
(148, 'Libye', 'ly'),
(149, 'Liechtenstein', 'li'),
(150, 'Lithuanie', 'lt'),
(151, 'Luxembourg', 'lu'),
(152, 'Macau', 'mo'),
(153, 'Macédoine', 'mk'),
(154, 'Madagascar', 'mg'),
(155, 'Malawi', 'mw'),
(156, 'Maldives (Îles)', 'mv'),
(157, 'Mali', 'ml'),
(158, 'Malte', 'mt'),
(159, 'Marshall (Îles)', 'mh'),
(160, 'Martinique', 'mq'),
(161, 'Mauritanie', 'mr'),
(162, 'Maurice', 'mu'),
(163, 'Mayotte', 'yt'),
(164, 'Micronésie (États fédérés de)', 'fm'),
(165, 'Moldavie', 'md'),
(166, 'Monaco', 'mc'),
(167, 'Mongolie', 'mn'),
(168, 'Montserrat', 'ms'),
(169, 'Mozambique', 'mz'),
(170, 'Myanmar', 'mm'),
(171, 'Namibie', 'na'),
(172, 'Nauru', 'nr'),
(173, 'Nepal', 'np'),
(174, 'Antilles néerlandaises', 'an'),
(175, 'Nouvelle Calédonie', 'nc'),
(176, 'Nicaragua', 'ni'),
(177, 'Niger', 'ne'),
(178, 'Nigeria', 'ng'),
(179, 'Niue', 'nu'),
(180, 'Norfolk (Îles)', 'nf'),
(181, 'Mariannes du Nord (Îles)', 'mp'),
(182, 'Oman', 'om'),
(183, 'Palau', 'pw'),
(184, 'Panama', 'pa'),
(185, 'Papouasie-Nouvelle-Guinée', 'pg'),
(186, 'Paraguay', 'py'),
(187, 'Pitcairn (Îles)', 'pn'),
(188, 'Qatar', 'qa'),
(189, 'Réunion (La)', 're'),
(190, 'Rwanda', 'rw'),
(191, 'Géorgie du Sud et Sandwich du Sud (Îles)', 'gs'),
(192, 'Saint-Kitts et Nevis', 'kn'),
(193, 'Sainte Lucie', 'lc'),
(194, 'Saint Vincent et les Grenadines', 'vc'),
(195, 'Samoa', 'ws'),
(196, 'Saint-Marin (Rép. de)', 'sm'),
(197, 'São Tomé et Príncipe (Rép.)', 'st'),
(198, 'Sénégal', 'sn'),
(199, 'Seychelles', 'sc'),
(200, 'Sierra Leone', 'sl'),
(201, 'Slovaquie', 'sk'),
(202, 'Slovénie', 'si'),
(203, 'Somalie', 'so'),
(204, 'Sri Lanka', 'lk'),
(205, 'Sainte Hélène', 'sh'),
(206, 'Saint Pierre et Miquelon', 'pm'),
(207, 'Soudan', 'sd'),
(208, 'Suriname', 'sr'),
(209, 'Svalbard et Jan Mayen (Îles)', 'sj'),
(210, 'Swaziland', 'sz'),
(211, 'Syrie', 'sy'),
(212, 'Tadjikistan', 'tj'),
(213, 'Tanzanie', 'tz'),
(214, 'Togo', 'tg'),
(215, 'Tokelau', 'tk'),
(216, 'Tonga', 'to'),
(217, 'Trinité et Tobago', 'tt'),
(218, 'Tunisie', 'tn'),
(219, 'Turkménistan', 'tm'),
(220, 'Turks et Caïques (Îles)', 'tc'),
(221, 'Tuvalu', 'tv'),
(222, 'Îles Mineures Éloignées des États-Unis', 'um'),
(223, 'Ouganda', 'ug'),
(224, 'Uruguay', 'uy'),
(225, 'Ouzbékistan', 'uz'),
(226, 'Vanuatu', 'vu'),
(227, 'Vatican (Etat du)', 'va'),
(228, 'Vietnam', 'vn'),
(229, 'Vierges (Îles)', 'vi'),
(230, 'Wallis et Futuna (Îles)', 'wf'),
(231, 'Sahara Occidental', 'eh'),
(232, 'Yemen', 'ye'),
(233, 'Zaïre', 'zr'),
(234, 'Zambie', 'zm'),
(235, 'Zimbabwe', 'zw'),
(236, 'La Barbad', 'bb');

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `id_question` int(11) NOT NULL AUTO_INCREMENT,
  `id_quizz_cours_question` int(11) DEFAULT NULL,
  `id_quizz_entrainement_question` int(11) DEFAULT NULL,
  `id_quizz_final_question` int(11) DEFAULT NULL,
  `texte_question` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `explication_question` varchar(800) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_question`),
  KEY `IDX_B6F7494EF7885782` (`id_quizz_cours_question`),
  KEY `IDX_B6F7494E45E7DB66` (`id_quizz_entrainement_question`),
  KEY `IDX_B6F7494E4FF5D76F` (`id_quizz_final_question`),
  KEY `id_quizz_entrainement_question` (`id_quizz_entrainement_question`,`id_quizz_cours_question`,`id_quizz_final_question`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=13 ;

--
-- Contenu de la table `question`
--

INSERT INTO `question` (`id_question`, `id_quizz_cours_question`, `id_quizz_entrainement_question`, `id_quizz_final_question`, `texte_question`, `explication_question`) VALUES
(11, 2, NULL, NULL, 'Q1', 'QQ1'),
(12, 2, NULL, NULL, 'Q2', 'QQ2');

-- --------------------------------------------------------

--
-- Structure de la table `quizz_chapitre`
--

CREATE TABLE IF NOT EXISTS `quizz_chapitre` (
  `id_quizz_entrainement` int(11) NOT NULL AUTO_INCREMENT,
  `id_chapitre_quizz_entrainement` int(11) DEFAULT NULL,
  `titre_quizz_entrainement` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_quizz_entrainement`),
  KEY `IDX_DB64C37CDD977F0` (`id_chapitre_quizz_entrainement`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `quizz_cours`
--

CREATE TABLE IF NOT EXISTS `quizz_cours` (
  `id_quizz_cours` int(11) NOT NULL AUTO_INCREMENT,
  `id_cours_quizz_cours` int(11) DEFAULT NULL,
  `titre_quizz_cours` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `description_quizz_cours` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `duree_quizz_cours` int(11) NOT NULL,
  PRIMARY KEY (`id_quizz_cours`),
  KEY `IDX_7338662B4B38EEB` (`id_cours_quizz_cours`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Contenu de la table `quizz_cours`
--

INSERT INTO `quizz_cours` (`id_quizz_cours`, `id_cours_quizz_cours`, `titre_quizz_cours`, `description_quizz_cours`, `duree_quizz_cours`) VALUES
(2, 9, 'Java', 'Java', 5);

-- --------------------------------------------------------

--
-- Structure de la table `quizz_final`
--

CREATE TABLE IF NOT EXISTS `quizz_final` (
  `id_quizz_final` int(11) NOT NULL AUTO_INCREMENT,
  `id_cours_quizz_final` int(11) DEFAULT NULL,
  `titre_quizz_final` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_quizz_final`),
  KEY `IDX_162C5638A5AC5EB1` (`id_cours_quizz_final`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE IF NOT EXISTS `reponse` (
  `id_reponse` int(11) NOT NULL AUTO_INCREMENT,
  `id_question_reponse` int(11) DEFAULT NULL,
  `texte_reponse` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `etat_reponse` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_reponse`),
  KEY `IDX_5FB6DEC7B2E05E0B` (`id_question_reponse`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=45 ;

--
-- Contenu de la table `reponse`
--

INSERT INTO `reponse` (`id_reponse`, `id_question_reponse`, `texte_reponse`, `etat_reponse`) VALUES
(41, 11, 'R1', 'vrai'),
(42, 11, 'R2', 'fausse'),
(43, 12, 'R1', 'vrai'),
(44, 12, 'R2', 'fausse');

-- --------------------------------------------------------

--
-- Structure de la table `score_quizz_cours`
--

CREATE TABLE IF NOT EXISTS `score_quizz_cours` (
  `id_score_quizz_cours` int(11) NOT NULL AUTO_INCREMENT,
  `id_apprenant_score_quizz_cours` int(11) DEFAULT NULL,
  `id_quizz_cours_score_quizz_cours` int(11) DEFAULT NULL,
  `score_score_quizz_cours` int(11) NOT NULL,
  `type_badge_score_quizz_cours` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_score_quizz_cours`),
  UNIQUE KEY `score_quizz_cours` (`id_apprenant_score_quizz_cours`,`id_quizz_cours_score_quizz_cours`),
  KEY `IDX_C2783717EE6B8EFD` (`id_apprenant_score_quizz_cours`),
  KEY `IDX_C27837172B9E9D59` (`id_quizz_cours_score_quizz_cours`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=14 ;

--
-- Contenu de la table `score_quizz_cours`
--

INSERT INTO `score_quizz_cours` (`id_score_quizz_cours`, `id_apprenant_score_quizz_cours`, `id_quizz_cours_score_quizz_cours`, `score_score_quizz_cours`, `type_badge_score_quizz_cours`) VALUES
(11, 19, 2, 20, 'BAD'),
(12, 20, 2, 10, 'BAD'),
(13, 21, 2, 20, 'BAD');

-- --------------------------------------------------------

--
-- Structure de la table `score_quizz_final`
--

CREATE TABLE IF NOT EXISTS `score_quizz_final` (
  `id_score_quizz` int(11) NOT NULL AUTO_INCREMENT,
  `id_apprenant_score_quizz_final` int(11) DEFAULT NULL,
  `id_quizz_final_score_quizz_final` int(11) DEFAULT NULL,
  `score_score_quizz` int(11) NOT NULL,
  `type_badge_score_quizz` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_score_quizz`),
  KEY `IDX_D367E74DFF745EA7` (`id_apprenant_score_quizz_final`),
  KEY `IDX_D367E74DD9E58E96` (`id_quizz_final_score_quizz_final`),
  KEY `id_apprenant_score_quizz_final` (`id_apprenant_score_quizz_final`,`id_quizz_final_score_quizz_final`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `sujet`
--

CREATE TABLE IF NOT EXISTS `sujet` (
  `id_sujet` int(11) NOT NULL AUTO_INCREMENT,
  `id_forum_sujet` int(11) DEFAULT NULL,
  `id_apprenant_sujet` int(11) DEFAULT NULL,
  `titre_sujet` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `soustitre_sujet` varchar(5000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description_sujet` longtext COLLATE utf8_unicode_ci NOT NULL,
  `nombre_message` int(11) DEFAULT NULL,
  `etat_sujet` int(11) DEFAULT NULL,
  `date_publication_sujet` datetime NOT NULL,
  `date_modification_sujet` datetime DEFAULT NULL,
  `aime_sujet` int(11) DEFAULT NULL,
  `last_poster` int(11) DEFAULT NULL,
  `last_poste` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_sujet`),
  KEY `IDX_EFD276992E7532BD` (`id_forum_sujet`),
  KEY `IDX_EFD2769930B84783` (`id_apprenant_sujet`),
  KEY `IDX_EFD276991EC239B0` (`last_poste`),
  KEY `FK_EFD27699A7768E28` (`last_poster`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=12 ;

--
-- Contenu de la table `sujet`
--

INSERT INTO `sujet` (`id_sujet`, `id_forum_sujet`, `id_apprenant_sujet`, `titre_sujet`, `soustitre_sujet`, `description_sujet`, `nombre_message`, `etat_sujet`, `date_publication_sujet`, `date_modification_sujet`, `aime_sujet`, `last_poster`, `last_poste`) VALUES
(2, 10, 19, ' MOOC Découvrez le framework PHP Laravel', ' MOOC Découvrez le framework PHP Larave', '<p>Bonjour &agrave; tous et bienvenue &agrave; tous ceux qui participent au cours &quot;D&eacute;couvrez le framework PHP Laravel&quot; !</p>\r\n\r\n<p>L&#39;adresse du cours :&nbsp;<a href="https://openclassrooms.com/courses/decouvrez-le-framework-php-laravel">http://openclassrooms.com/courses/decouvrez-le-framework-php-laravel</a></p>\r\n\r\n<p>Je vous invite &agrave; poster vos questions ici.</p>\r\n\r\n<p>J&#39;essaierai de passer r&eacute;guli&egrave;rement, mais n&#39;h&eacute;sitez pas &agrave; discuter entre vous et &agrave; vous entraider, en essayant de ne pas divulguer de solutions cependant.</p>\r\n\r\n<p>Si vous avez des probl&egrave;mes techniques avec les activit&eacute;s, vous pouvez contacter directement OpenClassrooms &agrave; l&#39;adresse suivante :&nbsp;<a href="mailto:hello@openclassrooms.com">hello@openclassrooms.com</a></p>\r\n\r\n<p>&Agrave; tr&egrave;s bient&ocirc;t !</p>\r\n', 1, 1, '2016-03-14 23:21:48', '2016-03-16 00:03:42', 1, 20, 7),
(3, 10, 19, 'MOOC Développez des sites web avec Symfony2', 'Bonjour à tous,', '<p>Bonjour &agrave; tous,</p>\r\n\r\n<p>J&#39;esp&egrave;re que votre rentr&eacute;e se passe bien, et que votre bonne r&eacute;solution cette ann&eacute;e est d&#39;apprendre de nouvelles technologies. Et vous avez raison, car le cours sur Symfony2 va bient&ocirc;t commencer ! La date pr&eacute;vue d&#39;ouverture est le lundi 22 septembre.</p>\r\n\r\n<p>Ce sujet vous permettra de poser vos questions tout au long de votre &eacute;volution dans le cours. La communaut&eacute; d&#39;utilisateurs de Symfony2, d&eacute;j&agrave; tr&egrave;s pr&eacute;sente sur OpenClassrooms, saura r&eacute;pondre &agrave; toutes vos questions ! Je serai &eacute;galement pr&eacute;sent pour vous aider dans votre avancement.</p>\r\n\r\n<p>En ce qui concerne les activit&eacute;s pr&eacute;sentes dans le cours, vous aurez &eacute;videmment pas mal de questions sur leur r&eacute;alisation. Il n&#39;y a aucun probl&egrave;me au contraire, par contre j&#39;appelle ceux qui ont les r&eacute;ponses &agrave; ne pas donner directement le r&eacute;sultat. En effet le but de ces activit&eacute;s est d&#39;apprendre par la pratique, il faut donc donner des indications, des conseils, mais laisser chercher ceux qui apprennent&nbsp;<img alt=";)" src="https://openclassrooms.com/bundles/tinymce/vendor/tiny_mce/plugins/emotions/img/clin.png" title=";)" /></p>\r\n\r\n<p>Merci et bon courage &agrave; tous !</p>\r\n\r\n<p>Edit : Le cours est maintenant ouvert et disponible &agrave; l&#39;adresse suivante :&nbsp;<a href="https://openclassrooms.com/courses/developpez-votre-site-web-avec-le-framework-symfony2">http://openclassrooms.com/courses/developpez-votre-site-web-avec-le-framework-symfony2</a></p>\r\n\r\n<p>Bonne lecture !</p>\r\n\r\n<p>Alexandre (winzou)</p>\r\n', 5, NULL, '2016-03-14 23:24:52', NULL, 1, 20, 1),
(4, 10, 20, 'MOOC Développez des sites web avec Symfony2', 'Bonjour à tous', '<p>J&#39;esp&egrave;re que votre rentr&eacute;e se passe bien, et que votre bonne r&eacute;solution cette ann&eacute;e est d&#39;apprendre de nouvelles technologies. Et vous avez raison, car le cours sur Symfony2 va bient&ocirc;t commencer ! La date pr&eacute;vue d&#39;ouverture est le lundi 22 septembre.</p>\r\n\r\n<p>Ce sujet vous permettra de poser vos questions tout au long de votre &eacute;volution dans le cours. La communaut&eacute; d&#39;utilisateurs de Symfony2, d&eacute;j&agrave; tr&egrave;s pr&eacute;sente sur OpenClassrooms, saura r&eacute;pondre &agrave; toutes vos questions ! Je serai &eacute;galement pr&eacute;sent pour vous aider dans votre avancement.</p>\r\n\r\n<p>En ce qui concerne les activit&eacute;s pr&eacute;sentes dans le cours, vous aurez &eacute;videmment pas mal de questions sur leur r&eacute;alisation. Il n&#39;y a aucun probl&egrave;me au contraire, par contre j&#39;appelle ceux qui ont les r&eacute;ponses &agrave; ne pas donner directement le r&eacute;sultat. En effet le but de ces activit&eacute;s est d&#39;apprendre par la pratique, il faut donc donner des indications, des conseils, mais laisser chercher ceux qui apprennent&nbsp;<img alt=";)" src="https://openclassrooms.com/bundles/tinymce/vendor/tiny_mce/plugins/emotions/img/clin.png" title=";)" /></p>\r\n\r\n<p>Merci et bon courage &agrave; tous !</p>\r\n\r\n<p>Edit : Le cours est maintenant ouvert et disponible &agrave; l&#39;adresse suivante :&nbsp;<a href="https://openclassrooms.com/courses/developpez-votre-site-web-avec-le-framework-symfony2">http://openclassrooms.com/courses/developpez-votre-site-web-avec-le-framework-symfony2</a></p>\r\n\r\n<p>Bonne lecture !</p>\r\n\r\n<p>Alexandre (winzou)</p>\r\n', 0, NULL, '2016-03-15 12:31:46', NULL, 11, 20, NULL),
(5, 2, 19, ' [iOS] Ressources', 'Les Ressources Mobiles', '<p>A la mani&egrave;re d&#39;AndroWiiid, je publie une liste de lien que j&#39;ai trouv&eacute; assez sympas</p>\r\n\r\n<h2>En fran&ccedil;ais :</h2>\r\n\r\n<ul>\r\n	<li><strong>CocoaCaf&eacute; -</strong><em>&nbsp;Le forum fran&ccedil;ais par excellence</em>&nbsp;:&nbsp;<a href="http://forum.cocoacafe.fr/">http://forum.cocoacafe.fr</a>&nbsp;(Attention, beaucoup de d&eacute;veloppeurs sur ce forum sont&nbsp;<strong>TRES&nbsp;</strong>exp&eacute;riment&eacute;s et parfois leurs r&eacute;ponses sont tranchantes si vous n&#39;avez pas recherch&eacute; votre erreur, plusieurs fois &eacute;tant plus jeune, j&#39;ai eu envie d&#39;arr&ecirc;ter le dev, tant ils &eacute;taient cinglants, mais ca remet les id&eacute;es en place... &quot;Ce qui ne nous tue pas nous rends plus fort :-)&quot;)</li>\r\n	<li>Le site des&nbsp;<strong>Cocoaheads</strong>, pour lesquels je donne plusieurs conf&eacute;rences par an :&nbsp;<a href="http://cocoaheads.fr/">http://cocoaheads.fr</a>&nbsp;(concernant la v2.0 de Lille je mettrais &eacute;galement les screencast de mes talks)</li>\r\n</ul>\r\n\r\n<h2>En anglais :</h2>\r\n\r\n<ul>\r\n	<li><strong>Biblioth&egrave;que officielle iOS :</strong><a href="http://developer.apple.com/library/ios/navigation/">http://developer.apple.com/library/ios/navigation/</a></li>\r\n	<li><strong>Un assez bon site avec &eacute;norm&eacute;ment de ressources :</strong><a href="http://www.raywenderlich.com/">http://www.raywenderlich.com</a></li>\r\n	<li><strong>Un screencast de tr&egrave;s bonne qualit&eacute;</strong><em>&nbsp;(fait par un copain)</em>&nbsp;:&nbsp;<a href="http://nsscreencast.com/">http://nsscreencast.com</a></li>\r\n	<li><strong>CocoaControls</strong>&nbsp;: parfois de tr&egrave;s bon trucs, parfois de tr&egrave;s mauvais :&nbsp;<a href="https://www.cocoacontrols.com/">https://www.cocoacontrols.com</a></li>\r\n	<li><strong><a href="http://maniacdev.com/">ManiacDev</a>&nbsp;:&nbsp;</strong>la caverne d&#39;Ali Baba du d&eacute;veloppeur iOS</li>\r\n	<li><strong>Un must have :&nbsp;<a href="http://cocoapods.org/">Cocopods&nbsp;</a></strong></li>\r\n	<li><strong><a href="http://www.appcoda.com/">AppCoda</a>&nbsp;:</strong>&nbsp;tutoriels d&#39;assez bonne qualit&eacute;&nbsp;</li>\r\n</ul>\r\n\r\n<h2>Les plus connus :</h2>\r\n\r\n<ul>\r\n	<li><strong>StackOverFlow</strong>&nbsp;(<strong>Tout les probl&egrave;mes y sont r&eacute;solus</strong>) :<a href="http://stackoverflow.com/questions/tagged/ios">&nbsp;http://stackoverflow.com/questions/tagged/ios</a></li>\r\n	<li><strong>Lynda.com</strong>&nbsp;(petite pub au passage je sortirais sur ce site un cours sur le d&eacute;veloppement de jeux vid&eacute;o avec Unity courant septembre) :&nbsp;<a href="http://www.lynda.com/Mobile-training-tutorials/55-0.html">http://www.lynda.com/Mobile-training-tutorials/55-0.html</a></li>\r\n	<li>Code School Try Objective-C (il existe un Try iOS aussi) :&nbsp;<a href="http://tryobjectivec.codeschool.com/">http://tryobjectivec.codeschool.com</a></li>\r\n	<li><strong><a href="https://github.com/">GitHub</a>&nbsp;:&nbsp;</strong>parce tout d&eacute;veloppeur qui ne l&#39;utilise pas... n&#39;est pas un d&eacute;veloppeur !</li>\r\n</ul>\r\n\r\n<h2>Les outils :</h2>\r\n\r\n<ul>\r\n	<li><strong><a href="http://cocoapods.org/">Cocoapods</a></strong>&nbsp;: gestionnaire de librairies</li>\r\n	<li><a href="http://cocoadocs.org/"><strong>Cocoadocs</strong></a>&nbsp;: gestionnaire de ... documentation</li>\r\n</ul>\r\n\r\n<h2>Des biblioth&egrave;ques int&eacute;ressantes :</h2>\r\n\r\n<ul>\r\n	<li><a href="https://github.com/AFNetworking/AFNetworking"><strong>AFNetworking</strong></a>&nbsp;: une lib au top, pour parser du JSON en asynchrone j&#39;en ai d&eacute;j&agrave; beaucoup parl&eacute; ici&nbsp;</li>\r\n	<li><a href="https://github.com/gotosleep/JASidePanels"><strong>JASidePanels</strong></a>&nbsp;: Un menu &agrave; la facebook (pour ceux qui utilisent des storyboards oubliez, bien que ce doit faisable)</li>\r\n	<li><strong><a href="https://code.google.com/p/core-plot/">CorePlot</a></strong>&nbsp;: Pour cr&eacute;er et &eacute;diter des graphes (mais il faut &ecirc;tre tr&egrave;s, tr&egrave;s bon, ceux qui n&#39;ont jamais utilis&eacute;s de Core Graphics, Core Animation vont souffrir)</li>\r\n	<li><strong><a href="https://github.com/youknowone/UI7Kit">UI7Kit</a>&nbsp;:&nbsp;</strong>J&#39;ai d&eacute;couvert &ccedil;a aujourd&#39;hui et c&#39;est COOOOOOL !</li>\r\n</ul>\r\n\r\n<h2>Des logiciels int&eacute;ressants :</h2>\r\n\r\n<ul>\r\n	<li>\r\n	<p>Soon&nbsp;</p>\r\n	</li>\r\n</ul>\r\n', 1, NULL, '2016-03-15 14:09:46', NULL, 0, 19, 16),
(10, 3, 20, ' Comment utiliser les classes du Framework .Net', 'Framework .Net', '<p style="text-align:justify">De nombreuses questions pos&eacute;es sur ce forum concernent l&#39;utilisation d&#39;une classe ou d&#39;une fonctionnalit&eacute; sp&eacute;cifique du Framework .Net.&nbsp;<br />\r\n<br />\r\nMais connaissez-vous la&nbsp;<strong>documentation officielle</strong>&nbsp;? Non seulement elle existe, mais en plus elle est tr&egrave;s compl&egrave;te et est vraiment bien faite. Il s&#39;agit de la&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/ms123401.aspx"><strong>MSDN Library</strong></a>&nbsp;et malgr&eacute; son nom, elle est bel et bien en fran&ccedil;ais !&nbsp;<img alt=":D" src="https://openclassrooms.com/bundles/tinymce/vendor/tiny_mce/plugins/emotions/img/heureux.png" /><br />\r\n<br />\r\nVous y trouverez en particulier le&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/52f3sw5c.aspx">guide d&#39;utilisation de Visual Studio</a>, toutes les informations utiles sur le&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/w0x726c2.aspx">Framework .Net</a>&nbsp;et sa&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/ms229335.aspx">biblioth&egrave;que de classes</a>,&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/dd642420.aspx">la pr&eacute;sentation des langages VB.NET et C#</a>&nbsp;(ainsi que leur r&eacute;f&eacute;rence d&eacute;taill&eacute;e:&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/25kad608.aspx">VB.NET</a>|<a href="http://msdn.microsoft.com/fr-fr/library/618ayhy6.aspx">C#</a>), sans oublier les&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/ms229042.aspx">r&egrave;gles de bonne pratique</a>&nbsp;et les&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/ms229002.aspx">conventions de nommage</a>.&nbsp;<br />\r\n<br />\r\nUne question sur une classe en particulier ? Utilisez la fonction recherche (en haut &agrave; gauche sur toute les pages). Exemple avec&nbsp;<a href="http://social.msdn.microsoft.com/Search/fr-FR?query=Console&amp;beta=0&amp;ac=3">&quot;Console&quot;</a>, qui vous permet de trouver facilement la&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/system.console.aspx">r&eacute;f&eacute;rence de la classe&nbsp;System.Console</a>.<br />\r\n<br />\r\nLes&nbsp;<strong><a href="http://msdn.microsoft.com/fr-fr/msdn.coach.aspx">Coach MSDN</a></strong>&nbsp;sont &eacute;galement utiles; il s&#39;agit de tutoriels sur les aspects cl&eacute;s du framework comme&nbsp;<a href="http://msdn.microsoft.com/fr-fr/netframework/msdn.coach.wpf.aspx">WPF</a>,&nbsp;<a href="http://msdn.microsoft.com/fr-fr/silverlight/bb187401">Silverlight</a>,&nbsp;<a href="http://msdn.microsoft.com/fr-fr/sqlserver/msdn.coach.sql.server.aspx">SQL Server</a>,&nbsp;<a href="http://msdn.microsoft.com/fr-fr/windowsazure/msdn.coach.azure.aspx">Windows Azure</a>&nbsp;et bien d&#39;autres !<br />\r\n<br />\r\nQuelques liens utiles en vrac:</p>\r\n\r\n<ul>\r\n	<li style="text-align:justify">Les&nbsp;<a href="http://msdn.microsoft.com/en-us/library/ms123401.aspx">Quick Links</a>&nbsp;(en anglais), pour aller &agrave; l&#39;essentiel !</li>\r\n	<li style="text-align:justify"><a href="http://msdn.microsoft.com/fr-fr/library/dd460654.aspx">Programmer en Orient&eacute; Objet</a>&nbsp;en VB.Net et en C#</li>\r\n	<li style="text-align:justify">Concevoir des interfaces graphiques avec les&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/cc656767.aspx">Windows Forms</a></li>\r\n	<li style="text-align:justify">Cr&eacute;er des interfaces encore plus riches et dynamiques avec&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/ms754130.aspx">WPF</a>&nbsp;!</li>\r\n	<li style="text-align:justify">D&eacute;velopper des sites web gr&acirc;ce &agrave;&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/bb400852.aspx">ASP.NET</a>&nbsp;et&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/aa286507">IIS</a></li>\r\n	<li style="text-align:justify">D&eacute;couvrir le Cloud Computing avec&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/dd179367.aspx">Windows Azure</a></li>\r\n	<li style="text-align:justify">Interagir avec une base de donn&eacute;es gr&acirc;ce &agrave;&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/e80y5yhx.aspx">ADO.NET</a>&nbsp;et/ou&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/bb399572.aspx">Entity Framework</a></li>\r\n	<li style="text-align:justify">Cr&eacute;er ses propres jeux pour Windows et Xbox 360 avec&nbsp;<a href="http://msdn.microsoft.com/en-us/library/bb200104.aspx">XNA</a></li>\r\n	<li style="text-align:justify">Voir aussi l&#39;<a href="http://create.msdn.com/en-US/">App Hub</a>&nbsp;pour d&eacute;velopper sur Xbox et Windows Phone 7 (en anglais)</li>\r\n	<li style="text-align:justify">D&eacute;velopper des applications riches pour le navigateur avec&nbsp;<a href="http://msdn.microsoft.com/en-us/library/cc838158%28VS.95%29.aspx">Silverlight</a></li>\r\n	<li style="text-align:justify">Faire communiquer ses applications gr&acirc;ce &agrave;&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/dd456779.aspx">WCF</a></li>\r\n	<li style="text-align:justify">Comprendre et utiliser&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/bb397926.aspx">LINQ</a>&nbsp;- Voir aussi&nbsp;<a href="http://msdn.microsoft.com/en-us/vcsharp/aa336746">101 LINQ Samples</a>&nbsp;(en anglais)</li>\r\n	<li style="text-align:justify">Une&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/a4t23ktk.aspx">vue d&#39;ensemble du Framework .Net</a>, pour mieux comprendre en quoi il consiste</li>\r\n	<li style="text-align:justify">Toutes les informations sur&nbsp;<a href="http://msdn.microsoft.com/fr-fr/sqlserver/default.aspx">SQL Server</a></li>\r\n	<li style="text-align:justify">Besoin de plus d&#39;exemples de code ? Consultez la&nbsp;<strong><a href="http://code.msdn.microsoft.com/">galerie</a></strong>&nbsp;! (en anglais)</li>\r\n</ul>\r\n\r\n<p style="text-align:justify"><br />\r\nBonne lecture !&nbsp;<img alt=":D" src="https://openclassrooms.com/bundles/tinymce/vendor/tiny_mce/plugins/emotions/img/heureux.png" /><br />\r\nEt rappelez-vous : vous n&#39;avez plus d&#39;excuse pour ne pas avoir consult&eacute; la doc avant de poser vos questions...&nbsp;<img alt=":diable:" src="https://openclassrooms.com/bundles/tinymce/vendor/tiny_mce/plugins/emotions/img/diable.png" /></p>\r\n', 1, 1, '2016-03-16 17:53:07', '2016-03-16 17:54:38', NULL, 20, 27),
(11, 3, 20, 'Lecture BDD remplissage GridView', 'BDD', '<p style="text-align: justify;">De nombreuses questions pos&eacute;es sur ce forum concernent l&#39;utilisation d&#39;une classe ou d&#39;une fonctionnalit&eacute; sp&eacute;cifique du Framework .Net.&nbsp;<br />\r\n<br />\r\nMais connaissez-vous la&nbsp;<strong>documentation officielle</strong>&nbsp;? Non seulement elle existe, mais en plus elle est tr&egrave;s compl&egrave;te et est vraiment bien faite. Il s&#39;agit de la&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/ms123401.aspx"><strong>MSDN Library</strong></a>&nbsp;et malgr&eacute; son nom, elle est bel et bien en fran&ccedil;ais !&nbsp;<img alt=":D" src="https://openclassrooms.com/bundles/tinymce/vendor/tiny_mce/plugins/emotions/img/heureux.png" /><br />\r\n<br />\r\nVous y trouverez en particulier le&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/52f3sw5c.aspx">guide d&#39;utilisation de Visual Studio</a>, toutes les informations utiles sur le&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/w0x726c2.aspx">Framework .Net</a>&nbsp;et sa&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/ms229335.aspx">biblioth&egrave;que de classes</a>,&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/dd642420.aspx">la pr&eacute;sentation des langages VB.NET et C#</a>&nbsp;(ainsi que leur r&eacute;f&eacute;rence d&eacute;taill&eacute;e:&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/25kad608.aspx">VB.NET</a>|<a href="http://msdn.microsoft.com/fr-fr/library/618ayhy6.aspx">C#</a>), sans oublier les&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/ms229042.aspx">r&egrave;gles de bonne pratique</a>&nbsp;et les&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/ms229002.aspx">conventions de nommage</a>.&nbsp;<br />\r\n<br />\r\nUne question sur une classe en particulier ? Utilisez la fonction recherche (en haut &agrave; gauche sur toute les pages). Exemple avec&nbsp;<a href="http://social.msdn.microsoft.com/Search/fr-FR?query=Console&amp;beta=0&amp;ac=3">&quot;Console&quot;</a>, qui vous permet de trouver facilement la&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/system.console.aspx">r&eacute;f&eacute;rence de la classe&nbsp;System.Console</a>.<br />\r\n<br />\r\nLes&nbsp;<strong><a href="http://msdn.microsoft.com/fr-fr/msdn.coach.aspx">Coach MSDN</a></strong>&nbsp;sont &eacute;galement utiles; il s&#39;agit de tutoriels sur les aspects cl&eacute;s du framework comme&nbsp;<a href="http://msdn.microsoft.com/fr-fr/netframework/msdn.coach.wpf.aspx">WPF</a>,&nbsp;<a href="http://msdn.microsoft.com/fr-fr/silverlight/bb187401">Silverlight</a>,&nbsp;<a href="http://msdn.microsoft.com/fr-fr/sqlserver/msdn.coach.sql.server.aspx">SQL Server</a>,&nbsp;<a href="http://msdn.microsoft.com/fr-fr/windowsazure/msdn.coach.azure.aspx">Windows Azure</a>&nbsp;et bien d&#39;autres !<br />\r\n<br />\r\nQuelques liens utiles en vrac:</p>\r\n\r\n<ul>\r\n	<li style="text-align: justify;">Les&nbsp;<a href="http://msdn.microsoft.com/en-us/library/ms123401.aspx">Quick Links</a>&nbsp;(en anglais), pour aller &agrave; l&#39;essentiel !</li>\r\n	<li style="text-align: justify;"><a href="http://msdn.microsoft.com/fr-fr/library/dd460654.aspx">Programmer en Orient&eacute; Objet</a>&nbsp;en VB.Net et en C#</li>\r\n	<li style="text-align: justify;">Concevoir des interfaces graphiques avec les&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/cc656767.aspx">Windows Forms</a></li>\r\n	<li style="text-align: justify;">Cr&eacute;er des interfaces encore plus riches et dynamiques avec&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/ms754130.aspx">WPF</a>&nbsp;!</li>\r\n	<li style="text-align: justify;">D&eacute;velopper des sites web gr&acirc;ce &agrave;&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/bb400852.aspx">ASP.NET</a>&nbsp;et&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/aa286507">IIS</a></li>\r\n	<li style="text-align: justify;">D&eacute;couvrir le Cloud Computing avec&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/dd179367.aspx">Windows Azure</a></li>\r\n	<li style="text-align: justify;">Interagir avec une base de donn&eacute;es gr&acirc;ce &agrave;&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/e80y5yhx.aspx">ADO.NET</a>&nbsp;et/ou&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/bb399572.aspx">Entity Framework</a></li>\r\n	<li style="text-align: justify;">Cr&eacute;er ses propres jeux pour Windows et Xbox 360 avec&nbsp;<a href="http://msdn.microsoft.com/en-us/library/bb200104.aspx">XNA</a></li>\r\n	<li style="text-align: justify;">Voir aussi l&#39;<a href="http://create.msdn.com/en-US/">App Hub</a>&nbsp;pour d&eacute;velopper sur Xbox et Windows Phone 7 (en anglais)</li>\r\n	<li style="text-align: justify;">D&eacute;velopper des applications riches pour le navigateur avec&nbsp;<a href="http://msdn.microsoft.com/en-us/library/cc838158%28VS.95%29.aspx">Silverlight</a></li>\r\n	<li style="text-align: justify;">Faire communiquer ses applications gr&acirc;ce &agrave;&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/dd456779.aspx">WCF</a></li>\r\n	<li style="text-align: justify;">Comprendre et utiliser&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/bb397926.aspx">LINQ</a>&nbsp;- Voir aussi&nbsp;<a href="http://msdn.microsoft.com/en-us/vcsharp/aa336746">101 LINQ Samples</a>&nbsp;(en anglais)</li>\r\n	<li style="text-align: justify;">Une&nbsp;<a href="http://msdn.microsoft.com/fr-fr/library/a4t23ktk.aspx">vue d&#39;ensemble du Framework .Net</a>, pour mieux comprendre en quoi il consiste</li>\r\n	<li style="text-align: justify;">Toutes les informations sur&nbsp;<a href="http://msdn.microsoft.com/fr-fr/sqlserver/default.aspx">SQL Server</a></li>\r\n	<li style="text-align: justify;">Besoin de plus d&#39;exemples de code ? Consultez la&nbsp;<strong><a href="http://code.msdn.microsoft.com/">galerie</a></strong>&nbsp;! (en anglais)</li>\r\n</ul>\r\n\r\n<p style="text-align: justify;"><br />\r\nBonne lecture !&nbsp;<img alt=":D" src="https://openclassrooms.com/bundles/tinymce/vendor/tiny_mce/plugins/emotions/img/heureux.png" /><br />\r\nEt rappelez-vous : vous n&#39;avez plus d&#39;excuse pour ne pas avoir consult&eacute; la doc avant de poser vos questions...&nbsp;<img alt=":diable:" src="https://openclassrooms.com/bundles/tinymce/vendor/tiny_mce/plugins/emotions/img/diable.png" /></p>\r\n', NULL, NULL, '2016-03-16 18:05:29', NULL, 2, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `thread`
--

CREATE TABLE IF NOT EXISTS `thread` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `permalink` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `is_commentable` tinyint(1) NOT NULL,
  `num_comments` int(11) NOT NULL,
  `last_comment_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `thread`
--

INSERT INTO `thread` (`id`, `permalink`, `is_commentable`, `num_comments`, `last_comment_at`) VALUES
('10', 'http://127.0.0.1/MOOCV5/web/app_dev.php/cours_details?id=10', 1, 0, NULL),
('11', 'http://127.0.0.1/MOOCV5/web/app_dev.php/cours_details?id=11', 1, 0, NULL),
('4', 'http://127.0.0.1/MOOCV5/web/app_dev.php/afficher_chapitre/4/9', 1, 0, NULL),
('9', 'http://127.0.0.1/MOOCV5/web/app_dev.php/cours_details?id=9', 1, 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `trophee`
--

CREATE TABLE IF NOT EXISTS `trophee` (
  `id_trophee` int(11) NOT NULL AUTO_INCREMENT,
  `id_apprenant_trophee` int(11) DEFAULT NULL,
  `id_cours_trophee` int(11) DEFAULT NULL,
  `chemin_trophee` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_trophee`),
  KEY `IDX_D06A5721FE244B36` (`id_apprenant_trophee`),
  KEY `IDX_D06A57212D44EF3F` (`id_cours_trophee`),
  KEY `id_cours_trophee` (`id_cours_trophee`,`id_apprenant_trophee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_entreprise_utilisateur` int(11) DEFAULT NULL,
  `information_entreprise_utilisateur` int(11) DEFAULT NULL,
  `information_formateur_utilisateur` int(11) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `locked` tinyint(1) NOT NULL,
  `expired` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL,
  `confirmation_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `credentials_expired` tinyint(1) NOT NULL,
  `credentials_expire_at` datetime DEFAULT NULL,
  `num_tel_utilisateur` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_1D1C63B392FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_1D1C63B3A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_1D1C63B39F3A3446` (`information_entreprise_utilisateur`),
  UNIQUE KEY `UNIQ_1D1C63B31C18BCE7` (`information_formateur_utilisateur`),
  KEY `IDX_1D1C63B32ABA784F` (`id_entreprise_utilisateur`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=67 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `id_entreprise_utilisateur`, `information_entreprise_utilisateur`, `information_formateur_utilisateur`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `locked`, `expired`, `expires_at`, `confirmation_token`, `password_requested_at`, `roles`, `credentials_expired`, `credentials_expire_at`, `num_tel_utilisateur`) VALUES
(19, NULL, NULL, 8, 'Souhaib Ben Farhat', 'souhaib ben farhat', 'souhaib.b.farhat@gmail.com', 'souhaib.b.farhat@gmail.com', 1, '', 'Souhaib Ben Farhat', '2016-03-16 19:14:45', 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:"ROLE_FORMATEUR";}', 0, NULL, NULL),
(20, NULL, NULL, NULL, 'Maha Akrout', 'maha akrout', 'maha.akrout@esprit.tn', 'maha.akrout@esprit.tn', 1, '', 'Maha Akrout', '2016-03-16 00:09:31', 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:"ROLE_APPRENANT";}', 0, NULL, NULL),
(21, NULL, NULL, NULL, 'Khaoula Touhiri', 'khaoula touhiri', 'khaoula.touihri@esprit.tn', 'khaoula.touihri@esprit.tn', 1, '', 'Khaoula Touhiri', '2016-03-16 18:54:06', 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:"ROLE_APPRENANT";}', 0, NULL, NULL),
(25, NULL, NULL, NULL, 'salah', 'salah', 'salah@gmail.com', 'salah@gmail.com', 0, '', 'salah', NULL, 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:ROLE_FORMATEUR;}', 0, NULL, NULL),
(26, NULL, NULL, NULL, 'ahmedAmine', 'ahmedamine', 'ahmedAmine@gmail.com', 'ahmedamine@gmail.com', 1, '', 'ahmedAmine', '2016-03-25 14:32:43', 1, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:"ROLE_FORMATEUR";}', 0, NULL, NULL),
(31, NULL, NULL, NULL, 'peter', 'peter', 'peter@gmail.com', 'peter@gmail.com', 1, '', 'peter', NULL, 1, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:"ROLE_FORMATEUR";}', 0, NULL, NULL),
(32, NULL, NULL, NULL, 'nihel', 'nihel', 'nihel@gmail.com', 'nihel@gmail.com', 1, '', 'nihel', NULL, 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:"ROLE_MEMBRE_COMITE";}', 0, NULL, NULL),
(43, NULL, NULL, NULL, 'salim', 'salim', 'salim@gmail.com', 'salim@gmail.com', 1, '', 'salim', NULL, 1, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:"ROLE_FORMATEUR";}', 0, NULL, NULL),
(44, NULL, NULL, NULL, 'saleh', 'saleh', 'saleh@gmail.com', 'saleh@gmail.com', 1, '', 'saleh', NULL, 1, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:"ROLE_FORMATEUR";}', 0, NULL, NULL),
(53, NULL, NULL, NULL, 'entreprise1', 'entreprise1', 'entreprise1@gmail.com', 'entreprise1@gmail.com', 1, '', 'entreprise1', NULL, 1, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:"ROLE_ENTREPRISE";}', 0, NULL, NULL),
(59, NULL, NULL, NULL, 'entreprise2', 'entreprise2', 'entreprise2@gmail.com', 'entreprise2@gmail.com', 1, '', 'entreprise2', NULL, 1, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:"ROLE_ENTREPRISE";}', 0, NULL, NULL),
(61, NULL, NULL, NULL, 'entreprise5', 'entreprise5', 'entreprise5@gmail.com', 'entreprise5@gmail.com', 1, '', 'entreprise5', NULL, 1, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:"ROLE_ENTREPRISE";}', 0, NULL, NULL),
(62, NULL, NULL, NULL, 'admin', 'admin', 'admin', 'admin', 1, '', 'admin', NULL, 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:16:"ROLE_SUPER_ADMIN";}', 0, NULL, NULL),
(63, NULL, NULL, NULL, 'apprenant1', 'apprenant1', 'apprenant1@gmail.com', 'apprenant1@gmail.com', 1, '', 'apprenant1', NULL, 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:ROLE_APPRENANT;}', 0, NULL, NULL),
(65, NULL, NULL, NULL, 'formateur10', 'formateur10', 'formateur10@gmail.com', 'formateur10@gmail.com', 1, '', 'formateur10', NULL, 1, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:"ROLE_FORMATEUR";}', 0, NULL, NULL),
(66, NULL, NULL, NULL, 'ffffff', 'ffffff', 'ffffff@ff.ff', 'ffffff@ff.ff', 1, '', 'ffffff', NULL, 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:14:ROLE_APPRENANT;}', 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `visite`
--

CREATE TABLE IF NOT EXISTS `visite` (
  `id_visite` int(11) NOT NULL AUTO_INCREMENT,
  `id_utilisateur` int(11) DEFAULT NULL,
  `id_cours` int(11) DEFAULT NULL,
  `date_visite` datetime NOT NULL,
  PRIMARY KEY (`id_visite`),
  KEY `IDX_B730898D50EAE44` (`id_utilisateur`),
  KEY `IDX_B730898D134FCDAC` (`id_cours`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=73 ;

--
-- Contenu de la table `visite`
--

INSERT INTO `visite` (`id_visite`, `id_utilisateur`, `id_cours`, `date_visite`) VALUES
(70, 21, 10, '2016-03-16 18:59:36'),
(71, 21, 9, '2016-03-16 19:02:18'),
(72, 21, 11, '2016-03-16 19:00:33');

-- --------------------------------------------------------

--
-- Structure de la table `vote`
--

CREATE TABLE IF NOT EXISTS `vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `voter_id` int(11) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_FA222A5AEBB4B8AD` (`voter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `aime`
--
ALTER TABLE `aime`
  ADD CONSTRAINT `FK_A86190D622580603` FOREIGN KEY (`id_utilisateur_aime`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FK_A86190D67A0878A8` FOREIGN KEY (`id_sujet_aime`) REFERENCES `sujet` (`id_sujet`);

--
-- Contraintes pour la table `appreciation`
--
ALTER TABLE `appreciation`
  ADD CONSTRAINT `FK_5CD4DEABC8036527` FOREIGN KEY (`id_cours_appreciation`) REFERENCES `cours` (`id_cours`),
  ADD CONSTRAINT `FK_5CD4DEABE5D8D4F8` FOREIGN KEY (`id_apprenant_appreciation`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `bibliotheque`
--
ALTER TABLE `bibliotheque`
  ADD CONSTRAINT `FK_4690D34DD24768C1` FOREIGN KEY (`id_cours_bibliotheque`) REFERENCES `cours` (`id_cours`),
  ADD CONSTRAINT `FK_4690D34DFF9CD91E` FOREIGN KEY (`id_apprenant_bibliotheque`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `chapitre`
--
ALTER TABLE `chapitre`
  ADD CONSTRAINT `FK_8C62B0257690A3FE` FOREIGN KEY (`id_cours_chapitre`) REFERENCES `cours` (`id_cours`);

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_5BC96BF0E2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  ADD CONSTRAINT `FK_5BC96BF0F675F31B` FOREIGN KEY (`author_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FK_67F068BCD2E32CA` FOREIGN KEY (`id_apprenant_commentaire`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FK_67F068BCD4706035` FOREIGN KEY (`id_cours_commentaire`) REFERENCES `cours` (`id_cours`);

--
-- Contraintes pour la table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `FK_FDCA8C9C59276A05` FOREIGN KEY (`id_discipline_cours`) REFERENCES `discipline` (`id_discipline`),
  ADD CONSTRAINT `FK_FDCA8C9CC92EF92C` FOREIGN KEY (`id_formateur_cours`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `forum`
--
ALTER TABLE `forum`
  ADD CONSTRAINT `FK_44EA91C921C65854` FOREIGN KEY (`id_discipline_forum`) REFERENCES `discipline` (`id_discipline`),
  ADD CONSTRAINT `FK_44EA91C94C586F86` FOREIGN KEY (`last_sujet`) REFERENCES `sujet` (`id_sujet`);

--
-- Contraintes pour la table `information_entreprise`
--
ALTER TABLE `information_entreprise`
  ADD CONSTRAINT `FK_414C97B9A4AEAFEA` FOREIGN KEY (`entreprise_id`) REFERENCES `utilisateur` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `information_formateur`
--
ALTER TABLE `information_formateur`
  ADD CONSTRAINT `FK_53FA7BCD155D8F51` FOREIGN KEY (`formateur_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `invitation_comite`
--
ALTER TABLE `invitation_comite`
  ADD CONSTRAINT `FK_90041155264B8AD5` FOREIGN KEY (`id_formateur_invitation_comite`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `invitation_entreprise_formateur`
--
ALTER TABLE `invitation_entreprise_formateur`
  ADD CONSTRAINT `FK_6AD34A936D43C268` FOREIGN KEY (`id_formateur`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FK_6AD34A93A8937AB7` FOREIGN KEY (`id_entreprise`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `invitation_formateur_entreprise`
--
ALTER TABLE `invitation_formateur_entreprise`
  ADD CONSTRAINT `FK_422B4B806D43C268` FOREIGN KEY (`id_formateur`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FK_422B4B80A8937AB7` FOREIGN KEY (`id_entreprise`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_790009E385A6EC73` FOREIGN KEY (`id_sujet_message`) REFERENCES `sujet` (`id_sujet`),
  ADD CONSTRAINT `FK_790009E38A5C5B26` FOREIGN KEY (`id_utilisateur_message`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `FK_A765AD322C55FF01` FOREIGN KEY (`id_proprietaire_notification`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FK_A765AD32BDA3681A` FOREIGN KEY (`id_utilisateur_notification`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FK_A765AD32F23EB44B` FOREIGN KEY (`id_sujet_notification`) REFERENCES `sujet` (`id_sujet`);

--
-- Contraintes pour la table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `FK_B6F7494E45E7DB66` FOREIGN KEY (`id_quizz_entrainement_question`) REFERENCES `quizz_chapitre` (`id_quizz_entrainement`),
  ADD CONSTRAINT `FK_B6F7494E4FF5D76F` FOREIGN KEY (`id_quizz_final_question`) REFERENCES `quizz_final` (`id_quizz_final`),
  ADD CONSTRAINT `FK_B6F7494EF7885782` FOREIGN KEY (`id_quizz_cours_question`) REFERENCES `quizz_cours` (`id_quizz_cours`);

--
-- Contraintes pour la table `quizz_chapitre`
--
ALTER TABLE `quizz_chapitre`
  ADD CONSTRAINT `FK_DB64C37CDD977F0` FOREIGN KEY (`id_chapitre_quizz_entrainement`) REFERENCES `chapitre` (`id_chapitre`);

--
-- Contraintes pour la table `quizz_cours`
--
ALTER TABLE `quizz_cours`
  ADD CONSTRAINT `FK_7338662B4B38EEB` FOREIGN KEY (`id_cours_quizz_cours`) REFERENCES `cours` (`id_cours`);

--
-- Contraintes pour la table `quizz_final`
--
ALTER TABLE `quizz_final`
  ADD CONSTRAINT `FK_162C5638A5AC5EB1` FOREIGN KEY (`id_cours_quizz_final`) REFERENCES `cours` (`id_cours`);

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `FK_5FB6DEC7B2E05E0B` FOREIGN KEY (`id_question_reponse`) REFERENCES `question` (`id_question`);

--
-- Contraintes pour la table `score_quizz_cours`
--
ALTER TABLE `score_quizz_cours`
  ADD CONSTRAINT `FK_C27837172B9E9D59` FOREIGN KEY (`id_quizz_cours_score_quizz_cours`) REFERENCES `quizz_cours` (`id_quizz_cours`),
  ADD CONSTRAINT `FK_C2783717EE6B8EFD` FOREIGN KEY (`id_apprenant_score_quizz_cours`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `score_quizz_final`
--
ALTER TABLE `score_quizz_final`
  ADD CONSTRAINT `FK_D367E74DD9E58E96` FOREIGN KEY (`id_quizz_final_score_quizz_final`) REFERENCES `quizz_final` (`id_quizz_final`),
  ADD CONSTRAINT `FK_D367E74DFF745EA7` FOREIGN KEY (`id_apprenant_score_quizz_final`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `sujet`
--
ALTER TABLE `sujet`
  ADD CONSTRAINT `FK_EFD276991EC239B0` FOREIGN KEY (`last_poste`) REFERENCES `message` (`id_message`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `FK_EFD276992E7532BD` FOREIGN KEY (`id_forum_sujet`) REFERENCES `forum` (`id_forum`),
  ADD CONSTRAINT `FK_EFD2769930B84783` FOREIGN KEY (`id_apprenant_sujet`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FK_EFD27699A7768E28` FOREIGN KEY (`last_poster`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `trophee`
--
ALTER TABLE `trophee`
  ADD CONSTRAINT `FK_D06A57212D44EF3F` FOREIGN KEY (`id_cours_trophee`) REFERENCES `cours` (`id_cours`),
  ADD CONSTRAINT `FK_D06A5721FE244B36` FOREIGN KEY (`id_apprenant_trophee`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FK_1D1C63B31C18BCE7` FOREIGN KEY (`information_formateur_utilisateur`) REFERENCES `information_formateur` (`id`),
  ADD CONSTRAINT `FK_1D1C63B32ABA784F` FOREIGN KEY (`id_entreprise_utilisateur`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FK_1D1C63B39F3A3446` FOREIGN KEY (`information_entreprise_utilisateur`) REFERENCES `information_entreprise` (`id`);

--
-- Contraintes pour la table `visite`
--
ALTER TABLE `visite`
  ADD CONSTRAINT `FK_B730898D134FCDAC` FOREIGN KEY (`id_cours`) REFERENCES `cours` (`id_cours`),
  ADD CONSTRAINT `FK_B730898D50EAE44` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `vote`
--
ALTER TABLE `vote`
  ADD CONSTRAINT `FK_FA222A5AEBB4B8AD` FOREIGN KEY (`voter_id`) REFERENCES `utilisateur` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
