package br.com.mreboucas.emailRest.rest.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.AttributedCharacterIterator.Attribute;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * @author Marcelo Rebouças 18 de jun de 2018 - 08:21:06 [marceloreboucas10@gmail.com]
 * @description 		
 */
public class UtilAux {

/*
	    private static final String CHAVE_IP_SERVIDOR_EMAIL = "IP_SERVIDOR_EMAIL";

	    private static final String EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$";

	    private static final String DOMINIO_CAGECE = "cagece.com.br";

	    protected static final String USERNAME = "noreply";

	    protected static final String PASSWORD = "IDSx6YYS996T1g8oRDDJ";

	    *//**
	     * Enviar email.
	     *
	     * @author Aldivone Correia[aldivone@gmail.com] - 24/04/2009 - 13:28:30
	     * @param mailServer
	     *          Ip do servidor de email.
	     * @param assunto
	     *          Assunto do Email.
	     * @param emailRementente
	     *          Email do rementente.
	     * @param emailDestinatario
	     *          Email do destinatï¿½rio.
	     * @param mensagem
	     *          Corpo do Email.
	     *//*
	    public static void enviarEmail(final String mailServer, final String assunto,
	            final String emailRementente, final String emailDestinatario,
	            final String mensagem)
	    {
	        try
	        {
	            if ( mailServer != null )
	            {
	                final Properties mailProps = new Properties();

	                // definiï¿½ï¿½o do mailserver
	                mailProps.put("mail.smtp.host", mailServer);

	                final Session mailSession = Session.getDefaultInstance(mailProps,
	                        authenticator());

	                
	                 * As duas linhas seguintes de cï¿½digo, colocam no formato de
	                 * endereï¿½os, supostamente vï¿½lidos, de email os dados passados pelos
	                 * parï¿½metros to e from.
	                 
	                final InternetAddress destinatario = new InternetAddress(
	                        emailDestinatario);

	                // Quando for o email gato do SA2 não precisa enviar.
	                if ( emailDestinatario != null
	                        && emailDestinatario.contains("naocadastrado@cagece") )
	                {
	                    return;
	                }

	                final InternetAddress remetente = new InternetAddress(emailRementente);
	                
	                 * As duas linhas de cï¿½digo a seguir, sï¿½o responsï¿½veis por setar
	                 * os atributos e propriedas necessï¿½rias do objeto message para que o
	                 * email seja enviado. inicializaï¿½ï¿½o do objeto Message.
	                 
	                final Message message = new MimeMessage(mailSession);

	                // Definiï¿½ï¿½o de quem estï¿½ enviando o email.
	                message.setFrom(remetente);

	                
	                 * define o(s) destinatï¿½rio(s) e qual o tipo do destinatï¿½rio. os
	                 * possï¿½veis tipos de destinatï¿½rio: TO, CC, BCC.
	                 
	                message.setRecipient(Message.RecipientType.TO, destinatario);

	                message.setSentDate(new Date());

	                // definiï¿½ï¿½o do assunto do email.
	                message.setSubject(assunto);

	                if ( mensagem != null )
	                {
	                    // definiï¿½ï¿½o do conteï¿½do da mensagem e do tipo da mensagem.
	                    message.setContent(mensagem.toString(),
	                            "text/html;  charset=ISO-8859-1");
	                }
	                else
	                {
	                    message.setContent("", "text/html; charset=ISO-8859-1");
	                }

	                // a linha de cï¿½digo seguinte ï¿½ a responsï¿½vel pelo envio do email.
	                Transport.send(message);
	            }
	        }
	        catch (final Exception e)
	        {
	            FacesUtils.log(Email.class,
	                    "Erro ao enviar e-mail, detalhes: " + e.getMessage());
	        }
	    }

	    *//**
	     *
	     * @author Dirceu da Silva [dirceu.rodrigues@cagece.com.br -
	     *         dirceusr@gmail.com] 13/03/2018 - 10:56:16.
	     * @return
	     *//*
	    private static Authenticator authenticator()
	    {
	        final Authenticator authenticator = new Authenticator()
	        {
	            private PasswordAuthentication authentication;
	            {
	                authentication = new PasswordAuthentication(USERNAME, PASSWORD);
	            }

	            @Override
	            protected PasswordAuthentication getPasswordAuthentication()
	            {
	                return authentication;
	            }
	        };
	        return authenticator;
	    }

	    *//**
	     * @see a prioridade 1 é alta, 3 normal e 5 baixa.
	     * @author Daniel Oliveira[daniel.andrade@cagece.com] 30/06/2016 - 16:17:51.
	     * @param mailServer
	     * @param assunto
	     * @param emailRementente
	     * @param emailDestinatario
	     * @param mensagem
	     * @param prioridade
	     *//*
	    private static void enviarEmailPrioridade(final String mailServer,
	            final String assunto, final String emailRementente,
	            final String emailDestinatario, final String mensagem,
	            final Integer prioridade)
	    {
	        try
	        {
	            if ( mailServer != null )
	            {
	                final Properties mailProps = new Properties();

	                // definiï¿½ï¿½o do mailserver
	                mailProps.put("mail.smtp.host", mailServer);

	                final Session mailSession = Session.getDefaultInstance(mailProps,
	                        authenticator());

	                
	                 * As duas linhas seguintes de cï¿½digo, colocam no formato de
	                 * endereï¿½os, supostamente vï¿½lidos, de email os dados passados pelos
	                 * parï¿½metros to e from.
	                 
	                final InternetAddress destinatario = new InternetAddress(
	                        emailDestinatario);

	                final InternetAddress remetente = new InternetAddress(emailRementente);
	                
	                 * As duas linhas de cï¿½digo a seguir, sï¿½o responsï¿½veis por setar
	                 * os atributos e propriedas necessï¿½rias do objeto message para que o
	                 * email seja enviado. inicializaï¿½ï¿½o do objeto Message.
	                 
	                final Message message = new MimeMessage(mailSession);

	                // Definiï¿½ï¿½o de quem estï¿½ enviando o email.
	                message.setFrom(remetente);

	                
	                 * define o(s) destinatï¿½rio(s) e qual o tipo do destinatï¿½rio. os
	                 * possï¿½veis tipos de destinatï¿½rio: TO, CC, BCC.
	                 
	                message.setRecipient(Message.RecipientType.TO, destinatario);

	                message.setSentDate(new Date());

	                // definiï¿½ï¿½o do assunto do email.
	                message.setSubject(assunto);

	                if ( mensagem != null )
	                {
	                    // definiï¿½ï¿½o do conteï¿½do da mensagem e do tipo da mensagem.
	                    message.setContent(mensagem.toString(),
	                            "text/html;  charset=ISO-8859-1");
	                }
	                else
	                {
	                    message.setContent("", "text/html; charset=ISO-8859-1");
	                }
	                // seta a prioridade(Marca a mensagem como importante)
	                if ( prioridade.equals(1) )
	                {
	                    message.addHeader("X-Priority", "1");
	                    message.addHeader("Importance", "High");
	                    message.addHeader("X-MSMail-Priority", "High");
	                }
	                else if ( prioridade.equals(3) )
	                {
	                    message.addHeader("X-Priority", "3"); // normal
	                }
	                else if ( prioridade.equals(5) )
	                {
	                    message.addHeader("X-Priority", "5"); // low
	                }
	                // a linha de cï¿½digo seguinte ï¿½ a responsï¿½vel pelo envio do email.
	                Transport.send(message);
	            }
	        }
	        catch (final Exception e)
	        {
	            FacesUtils.log(Email.class,
	                    "Erro ao enviar e-mail, detalhes: " + e.getMessage());
	        }
	    }

	  
	    *//**
	     * Pesquisa dns disponï¿½veis, atravï¿½s do email informado;
	     *
	     * a travï¿½s da fï¿½brica de contexto "com.sun.jndi.dns.DnsContextFactory" e
	     * InitialDirContext para realizar pesquisa de DNS.
	     *
	     * @author Dirceu da Silva [dirceu.rodrigues@cagece.com.br -
	     *         dirceusr@gmail.com] 03/04/2014 - 15:08:20.
	     * @param email
	     * @param timeout
	     * @param tentativa
	     * @return
	     *//*
	    @SuppressWarnings({ "rawtypes", "unchecked" })
	    public static boolean validaDominioEmail(final String email,
	            final String timeout, final String tentativa)

	    {
	        try
	        {
	            final String dominio = getDominio(email);

	            if ( dominio.equals(DOMINIO_CAGECE) )
	            {
	                return true;
	            }

	            final java.util.Hashtable env = new java.util.Hashtable();
	            env.put("java.naming.factory.initial",
	                    "com.sun.jndi.dns.DnsContextFactory");
	            env.put("com.sun.jndi.dns.timeout.initial", timeout);
	            env.put("com.sun.jndi.dns.timeout.retries", tentativa);
	            env.put("java.naming.provider.url", "dns:");

	            final DirContext ictx = new InitialDirContext(env);
	            // DNS record types MX:mail exchange record
	            final javax.naming.directory.Attributes attrs = ictx
	                    .getAttributes(dominio, new String[] { "MX" });
	            final Attribute attr = attrs.get("MX");
	            final boolean retorno = attr != null && attr.size() > 0 ? true : false;
	            ictx.close();
	            return retorno;
	        }
	        catch (final NameNotFoundException e)
	        {
	            return false;
	        }
	        catch (final NamingException e)
	        {
	            return true;
	        }
	        catch (final Exception e)
	        {
	            return true;
	        }
	    }

	    *//**
	     *
	     * @author Dirceu da Silva [dirceu.rodrigues@cagece.com.br -
	     *         dirceusr@gmail.com] 22/05/2013 - 16:25:56.
	     * @param dominio
	     * @param ictx
	     * @param attr
	     * @return
	     * @throws NamingException
	     *//*
	    @SuppressWarnings({ "rawtypes", "unused" })
	    private static List<String> getListIpDominio(final String dominio,
	            final DirContext ictx, Attribute attr) throws NamingException
	    {
	        javax.naming.directory.Attributes attrs;

	        // DNS record types A:address record
	        attrs = ictx.getAttributes(dominio, new String[] { "A" });
	        attr = attrs.get("A");

	        final List<String> result = new ArrayList<>();
	        if ( attr != null && attr.size() > 0 )
	        {
	            final NamingEnumeration e = attr.getAll();

	            while (e.hasMore())
	            {
	                result.add((String) e.next());
	            }
	        }
	        return result;
	    }

	    public static String getDominio(final String email)
	    {
	        int pos = email.indexOf('@');

	        if ( pos == -1 )
	        {
	            return "";
	        }
	        final String domain = email.substring(++pos);

	        return domain;
	    }

	    *//**
	     * Mï¿½todo que envia email e retorna true se enviado. Mï¿½todo usado no
	     * atendimento mobile(Ocorrï¿½ncia)
	     *
	     * @author Felipe R. Oliveira[felipe.rodrigues@cagece.com.br] 22/04/2013 -
	     *         09:40:35.
	     * @param mailServer
	     * @param assunto
	     * @param emailRementente
	     * @param emailDestinatario
	     * @param mensagem
	     *//*
	    public static Boolean enviarEmailEvalidar(final String assunto,
	            final String emailDestinatario, final String mensagem)
	    {
	        final String mailServer = getIpServidorEmail();
	        final String emailRementente = Constants.EMAIL_PRAX;

	        try
	        {
	            final Properties mailProps = new Properties();

	            // definiï¿½ï¿½o do mailserver
	            mailProps.put("mail.smtp.host", mailServer);

	            final Session mailSession = Session.getDefaultInstance(mailProps,
	                    authenticator());

	            
	             * As duas linhas seguintes de cï¿½digo, colocam no formato de
	             * endereï¿½os, supostamente vï¿½lidos, de email os dados passados pelos
	             * parï¿½metros to e from.
	             
	            final InternetAddress destinatario = new InternetAddress(
	                    emailDestinatario);

	            final InternetAddress remetente = new InternetAddress(emailRementente,
	                    "ATENDIMENTO CAGECE");
	            
	             * As duas linhas de cï¿½digo a seguir, sï¿½o responsï¿½veis por setar os
	             * atributos e propriedas necessï¿½rias do objeto message para que o email
	             * seja enviado. inicializaï¿½ï¿½o do objeto Message.
	             
	            final Message message = new MimeMessage(mailSession);

	            // Definiï¿½ï¿½o de quem estï¿½ enviando o email.
	            message.setFrom(remetente);

	            
	             * define o(s) destinatï¿½rio(s) e qual o tipo do destinatï¿½rio. os
	             * possï¿½veis tipos de destinatï¿½rio: TO, CC, BCC.
	             
	            message.setRecipient(Message.RecipientType.TO, destinatario);

	            message.setSentDate(new Date());

	            // definiï¿½ï¿½o do assunto do email.
	            message.setSubject(assunto);

	            if ( mensagem != null )
	            {
	                // definiï¿½ï¿½o do conteï¿½do da mensagem e do tipo da mensagem.
	                message.setContent(mensagem.toString(),
	                        "text/html;  charset=ISO-8859-1");
	            }
	            else
	            {
	                message.setContent("", "text/html; charset=ISO-8859-1");
	            }

	            // a linha de cï¿½digo seguinte ï¿½ a responsï¿½vel pelo envio do email.
	            Transport.send(message);
	        }
	        catch (final Exception e)
	        {
	            FacesUtils.log(Email.class,
	                    "Erro ao enviar e-mail, detalhes: " + e.getMessage());
	            // Return error
	            return false;
	        }
	        // Message is send
	        return true;
	    }

	    *//**
	     *
	     * Mï¿½todo que envia emails noreply com template para 1 ou mï¿½ltiplos
	     * destinatï¿½rios.
	     *
	     * @author Josï¿½ Braga[josebraga.neto@cagece.com.br] 21/01/2014 - 10:34:03.
	     * @param assunto
	     * @param emailDestinatario
	     * @param mensagem
	     * @return
	     *//*
	    public static void enviarEmailNoReply(final String assunto,
	            final List<String> emailDestinatarios, final String mensagem,
	            final String aliasRemetente)
	    {
	        final String mailServer = getIpServidorEmail();
	        final String emailRementente = Constants.EMAIL_CAGECE_NO_REPLY;

	        try
	        {
	            final Properties mailProps = new Properties();

	            // definiï¿½ï¿½o do mailserver
	            mailProps.put("mail.smtp.host", mailServer);

	            final Session mailSession = Session.getDefaultInstance(mailProps,
	                    authenticator());

	            
	             * Alimenta as listas de destinatï¿½rios
	             
	            final List<InternetAddress> emailsDest = new ArrayList<>();
	            InternetAddress email = null;

	            if ( !TypeUtils.isBlankOrNullOrEmpty(emailDestinatarios) )
	            {
	                for (final String e : emailDestinatarios)
	                {
	                    email = new InternetAddress(e);
	                    emailsDest.add(email);
	                }
	            }

	            final InternetAddress[] arrayEmailDest = emailsDest
	                    .toArray(new InternetAddress[emailsDest.size()]);

	            
	             * O remetente ï¿½ o e-mail noreply@cagece.com.br
	             
	            final InternetAddress remetente = new InternetAddress(emailRementente,
	                    aliasRemetente);
	            
	             * As duas linhas de cï¿½digo a seguir, sï¿½o responsï¿½veis por setar os
	             * atributos e propriedas necessï¿½rias do objeto message para que o email
	             * seja enviado. inicializaï¿½ï¿½o do objeto Message.
	             
	            final Message message = new MimeMessage(mailSession);

	            
	             * Definiï¿½ï¿½o de quem estï¿½ enviando o email.
	             
	            message.setFrom(remetente);

	            
	             * Define o(s) destinatï¿½rio(s) e qual o tipo do destinatï¿½rio. os
	             * possï¿½veis tipos de destinatï¿½rio: TO, CC, BCC.
	             
	            message.setRecipients(Message.RecipientType.TO, arrayEmailDest);

	            message.setSentDate(new Date());

	            
	             * Definiï¿½ï¿½o do assunto do email.
	             
	            message.setSubject(assunto);

	            if ( mensagem != null )
	            {
	                
	                 * Definiï¿½ï¿½o do conteï¿½do da mensagem e do tipo da mensagem.
	                 
	                message.setContent(mensagem.toString(),
	                        "text/html;  charset=ISO-8859-1");
	            }
	            else
	            {
	                message.setContent("", "text/html; charset=ISO-8859-1");
	            }

	            
	             * A linha de cï¿½digo seguinte ï¿½ a responsï¿½vel pelo envio do email.
	             
	            Transport.send(message);
	        }
	        catch (final Exception e)
	        {
	            FacesUtils.log(Email.class,
	                    "Erro ao enviar e-mail, detalhes: " + e.getMessage());
	        }
	    }

	    public static void enviarEmail(final String mailServer, final String assunto,
	            final String emailRementente, final String emailDestinatario,
	            final String mensagem, final byte[] anexo, final String nomeArquivoAnexo)
	    {
	        enviarEmail(mailServer, assunto, emailRementente, null, emailDestinatario,
	                mensagem, null, anexo, nomeArquivoAnexo,
	                FacesUtils.MIMETYPE_APPLICATION_PDF);
	    }

	    public static void enviarEmailHtml(String mailServer, final String assunto,
	            final String aliasRemetente, final String emailDestinatario,
	            final String mensagem, final byte[] anexo, final String nomeArquivoAnexo,
	            final String mimeType)
	    {
	        try
	        {
	            final Properties mailProps = new Properties();
	            if ( mailServer == null )
	            {
	                mailServer = getIpServidorEmail();
	            }

	            // definiï¿½ï¿½o do mailserver
	            mailProps.put("mail.smtp.host", mailServer);

	            final Session mailSession = Session.getDefaultInstance(mailProps,
	                    authenticator());

	            
	             * As duas linhas seguintes de cï¿½digo, colocam no formato de
	             * endereï¿½os, supostamente vï¿½lidos, de email os dados passados pelos
	             * parï¿½metros to e from.
	             
	            final InternetAddress destinatario = new InternetAddress(
	                    emailDestinatario);

	            final InternetAddress remetente = new InternetAddress(
	                    Constants.EMAIL_CAGECE_NO_REPLY, aliasRemetente);
	            
	             * As duas linhas de cï¿½digo a seguir, sï¿½o responsï¿½veis por setar os
	             * atributos e propriedas necessï¿½rias do objeto message para que o email
	             * seja enviado. inicializaï¿½ï¿½o do objeto Message.
	             
	            final Message message = new MimeMessage(mailSession);

	            // Definiï¿½ï¿½o de quem estï¿½ enviando o email.
	            message.setFrom(remetente);

	            
	             * define o(s) destinatï¿½rio(s) e qual o tipo do destinatï¿½rio. os
	             * possï¿½veis tipos de destinatï¿½rio: TO, CC, BCC.
	             
	            message.setRecipient(Message.RecipientType.TO, destinatario);

	            message.setSentDate(new Date());

	            // definiï¿½ï¿½o do assunto do email.
	            message.setSubject(assunto);

	            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
	            message.setHeader("Content-Transfer-Encoding", "quoted-printable");

	            final Multipart corpo = new MimeMultipart();

	            // final InternetHeaders headers = new InternetHeaders();
	            //
	            // headers.addHeader("Content-Type", "application/pdf");

	            final MimeBodyPart msg = new MimeBodyPart();

	            msg.setContent(mensagem, "text/html; charset=UTF-8");

	            final MimeBodyPart parAnexo = new MimeBodyPart();

	            parAnexo.setDataHandler(
	                    new DataHandler(new ByteArrayDataSource(anexo, mimeType)));

	            if ( mimeType != null
	                    && (mimeType.equals(FacesUtils.MIMETYPE_APPLICATION_PDF)
	                            || mimeType.equals("aplication/pdf")) )
	            {
	                parAnexo.setFileName(nomeArquivoAnexo + ".pdf");
	            }
	            else if ( mimeType != null
	                    && mimeType.equals(FacesUtils.MIMETYPE_TEXT_TXT) )
	            {
	                parAnexo.setFileName(nomeArquivoAnexo + ".txt");
	            }
	            else if ( mimeType != null
	                    && mimeType.equals(FacesUtils.MIMETYPE_APPLICATION_JPEG) )
	            {
	                parAnexo.setFileName(nomeArquivoAnexo + ".jpg");
	            }
	            else
	            {
	                parAnexo.setFileName(nomeArquivoAnexo);
	            }

	            corpo.addBodyPart(msg);
	            corpo.addBodyPart(parAnexo);

	            message.setContent(corpo);

	            // a linha de cï¿½digo seguinte ï¿½ a responsï¿½vel pelo envio do email.
	            Transport.send(message);
	        }
	        catch (final Exception e)
	        {
	            FacesUtils.log(Email.class,
	                    "Erro ao enviar e-mail, detalhes: " + e.getMessage());
	        }
	    }

	    *//**
	     *
	     * @author Igor Leal [igor.leal@cagece.com.br] 12/02/2015 - 08:44:48.
	     * @param mailServer
	     * @param assunto
	     * @param emailRementente
	     * @param aliasRemetente
	     *          TODO
	     * @param emailDestinatario
	     * @param mensagem
	     * @param contentType
	     *          TODO
	     * @param anexo
	     * @param nomeArquivoAnexo
	     * @param extensao
	     *//*
	    public static void enviarEmail(String mailServer, final String assunto,
	            final String emailRementente, final String aliasRemetente,
	            final String emailDestinatario, final String mensagem,
	            final String contentType, final byte[] anexo,
	            final String nomeArquivoAnexo, final String mimeType)
	    {
	        try
	        {
	            final Properties mailProps = new Properties();
	            if ( mailServer == null )
	            {
	                mailServer = getIpServidorEmail();
	            }

	            // definiï¿½ï¿½o do mailserver
	            mailProps.put("mail.smtp.host", mailServer);
	            mailProps.put("mail.smtp.auth", "true");
	            mailProps.put("mail.smtp.port", 587);
	            // mailProps.put("mail.debug", "true");

	            final Session mailSession = Session.getDefaultInstance(mailProps,
	                    authenticator());
	            // mailSession.setDebug(true);

	            
	             * As duas linhas seguintes de cï¿½digo, colocam no formato de
	             * endereï¿½os, supostamente vï¿½lidos, de email os dados passados pelos
	             * parï¿½metros to e from.
	             
	            final InternetAddress destinatario = new InternetAddress(
	                    emailDestinatario);

	            InternetAddress remetente;
	            if ( aliasRemetente != null )
	            {
	                remetente = new InternetAddress(emailRementente, aliasRemetente);
	            }
	            else
	            {
	                remetente = new InternetAddress(emailRementente);
	            }
	            
	             * As duas linhas de cï¿½digo a seguir, sï¿½o responsï¿½veis por setar os
	             * atributos e propriedas necessï¿½rias do objeto message para que o email
	             * seja enviado. inicializaï¿½ï¿½o do objeto Message.
	             
	            final Message message = new MimeMessage(mailSession);

	            // Definiï¿½ï¿½o de quem estï¿½ enviando o email.
	            message.setFrom(remetente);

	            
	             * define o(s) destinatï¿½rio(s) e qual o tipo do destinatï¿½rio. os
	             * possï¿½veis tipos de destinatï¿½rio: TO, CC, BCC.
	             
	            message.setRecipient(Message.RecipientType.TO, destinatario);

	            message.setSentDate(new Date());

	            // definiï¿½ï¿½o do assunto do email.
	            message.setSubject(assunto);

	            final String content = contentType == null ? "text/plain" : contentType;

	            message.setHeader("Content-Type", content + ";  charset=UTF-8");

	            message.setHeader("Content-Transfer-Encoding", "quoted-printable");

	            final Multipart corpo = new MimeMultipart();

	            // final InternetHeaders headers = new InternetHeaders();
	            //
	            // headers.addHeader("Content-Type", "application/pdf");

	            final MimeBodyPart msg = new MimeBodyPart();

	            msg.setContent(mensagem, content + "; charset=UTF-8");

	            final MimeBodyPart parAnexo = new MimeBodyPart();

	            parAnexo.setDataHandler(
	                    new DataHandler(new ByteArrayDataSource(anexo, mimeType)));

	            if ( mimeType != null
	                    && mimeType.equals(FacesUtils.MIMETYPE_APPLICATION_PDF) )
	            {
	                parAnexo.setFileName(nomeArquivoAnexo + ".pdf");
	            }
	            else if ( mimeType != null
	                    && mimeType.equals(FacesUtils.MIMETYPE_TEXT_TXT) )
	            {
	                parAnexo.setFileName(nomeArquivoAnexo + ".txt");
	            }
	            else if ( mimeType != null
	                    && mimeType.equals(FacesUtils.MIMETYPE_APPLICATION_JPEG) )
	            {
	                parAnexo.setFileName(nomeArquivoAnexo + ".jpg");
	            }
	            else if ( mimeType != null
	                    && mimeType.equals(FacesUtils.MIMETYPE_APPLICATION_EXCEL) )
	            {
	                parAnexo.setFileName(nomeArquivoAnexo + ".xls");
	            }
	            else
	            {
	                parAnexo.setFileName(nomeArquivoAnexo);
	            }

	            corpo.addBodyPart(msg);
	            corpo.addBodyPart(parAnexo);

	            message.setContent(corpo);

	            Transport.send(message);
	        }
	        catch (final Exception e)
	        {
	            FacesUtils.log(Email.class,
	                    "Erro ao enviar e-mail, detalhes: " + imprimirExcecaoEmString(e));
	        }
	    }

	    *//**
	     *
	     * Envia email com template html noreply(sem direito de resposta) com anexo.
	     *
	     * @author Josï¿½ Braga[josebraga.neto@cagece.com.br] 13/02/2014 - 10:12:23.
	     * @param mailServer
	     * @param assunto
	     * @param emailRementente
	     * @param emailDestinatario
	     * @param mensagem
	     * @param anexo
	     * @param nomeArquivoAnexo
	     *//*
	    public static void enviarEmailNoReplyComAnexo(final String assunto,
	            final String emailDestinatario, final String aliasRemetente,
	            final String mensagem, final byte[] anexo, final String nomeArquivoAnexo)
	    {
	        try
	        {
	            final Properties mailProps = new Properties();

	            // definiï¿½ï¿½o do mailserver
	            mailProps.put("mail.smtp.host", getIpServidorEmail());
	            mailProps.put("mail.smtp.connectiontimeout", "1000");
	            mailProps.put("mail.smtp.timeout", "1000");

	            final Session mailSession = Session.getDefaultInstance(mailProps,
	                    authenticator());

	            
	             * As duas linhas seguintes de cï¿½digo, colocam no formato de
	             * endereï¿½os, supostamente vï¿½lidos, de email os dados passados pelos
	             * parï¿½metros to e from.
	             
	            final InternetAddress destinatario = new InternetAddress(
	                    emailDestinatario);

	            final InternetAddress remetente = new InternetAddress(
	                    Constants.EMAIL_CAGECE_NO_REPLY, aliasRemetente);
	            
	             * As duas linhas de cï¿½digo a seguir, sï¿½o responsï¿½veis por setar os
	             * atributos e propriedas necessï¿½rias do objeto message para que o email
	             * seja enviado. inicializaï¿½ï¿½o do objeto Message.
	             
	            final Message message = new MimeMessage(mailSession);

	            // Definiï¿½ï¿½o de quem estï¿½ enviando o email.
	            message.setFrom(remetente);

	            
	             * define o(s) destinatï¿½rio(s) e qual o tipo do destinatï¿½rio. os
	             * possï¿½veis tipos de destinatï¿½rio: TO, CC, BCC.
	             
	            message.setRecipient(Message.RecipientType.TO, destinatario);

	            message.setSentDate(new Date());

	            // definiï¿½ï¿½o do assunto do email.
	            message.setSubject(assunto);

	            message.setHeader("Content-Transfer-Encoding", "quoted-printable");

	            final Multipart corpo = new MimeMultipart();

	            final MimeBodyPart msg = new MimeBodyPart();

	            
	             * Definiï¿½ï¿½o do conteï¿½do da mensagem e do tipo da mensagem.
	             
	            msg.setContent(mensagem.toString(), "text/html;  charset=ISO-8859-1");

	            final MimeBodyPart parAnexo = new MimeBodyPart();

	            parAnexo.setDataHandler(
	                    new DataHandler(new ByteArrayDataSource(anexo, "aplication/pdf")));

	            parAnexo.setFileName(nomeArquivoAnexo + ".pdf");

	            corpo.addBodyPart(msg);
	            corpo.addBodyPart(parAnexo);

	            message.setContent(corpo);

	            // a linha de cï¿½digo seguinte ï¿½ a responsï¿½vel pelo envio do email.
	            Transport.send(message);
	        }
	        catch (final Exception e)
	        {
	            FacesUtils.log(Email.class,
	                    "Erro ao enviar e-mail, detalhes: " + e.getMessage());
	        }
	    }

	    *//**
	     * Envia email para mais de um destinatï¿½rio principal e com cï¿½pia com
	     * anexo.
	     *
	     *
	     * @author Brunno Goncalves de Oliveira[brunno.oliveira@cagece.com.br]
	     *         19/09/2011 - 14:08:00.
	     * @param mailServer
	     * @param assunto
	     * @param emailRementente
	     * @param emailDestinatarios
	     * @param emailDestinatariosCC
	     * @param mensagem
	     * @param anexo
	     * @param nomeArquivoAnexo
	     *//*
	    public static void enviarEmail(final String mailServer, final String assunto,
	            final String emailRementente, final List<String> emailDestinatarios,
	            final List<String> emailDestinatariosCC, final String mensagem,
	            final byte[] anexo, final String nomeArquivoAnexo)
	    {
	        try
	        {
	            final Properties mailProps = new Properties();

	            // definiï¿½ï¿½o do mailserver
	            mailProps.put("mail.smtp.host", getIpServidorEmail());

	            final Session mailSession = Session.getDefaultInstance(mailProps,
	                    authenticator());

	            
	             * Alimenta as listas de destinatï¿½rios
	             
	            final List<InternetAddress> emailsDest = new ArrayList<>();
	            final List<InternetAddress> emailsDestCC = new ArrayList<>();
	            InternetAddress email = null;

	            if ( !TypeUtils.isBlankOrNullOrEmpty(emailDestinatarios) )
	            {
	                for (final String e : emailDestinatarios)
	                {
	                    email = new InternetAddress(e);
	                    emailsDest.add(email);
	                }

	            }

	            if ( !TypeUtils.isBlankOrNullOrEmpty(emailDestinatariosCC) )
	            {
	                for (final String e : emailDestinatariosCC)
	                {
	                    email = new InternetAddress(e);
	                    emailsDestCC.add(email);
	                }
	            }

	            final InternetAddress[] arrayEmailDest = emailsDest
	                    .toArray(new InternetAddress[emailsDest.size()]);
	            final InternetAddress[] arrayEmailDestCC = emailsDestCC
	                    .toArray(new InternetAddress[emailsDestCC.size()]);

	            
	             * As duas linhas seguintes de cï¿½digo, colocam no formato de
	             * endereï¿½os, supostamente vï¿½lidos, de email os dados passados pelos
	             * parï¿½metros to e from.
	             
	            final InternetAddress remetente = new InternetAddress(emailRementente);
	            
	             * As duas linhas de cï¿½digo a seguir, sï¿½o responsï¿½veis por setar os
	             * atributos e propriedas necessï¿½rias do objeto message para que o email
	             * seja enviado. inicializaï¿½ï¿½o do objeto Message.
	             
	            final Message message = new MimeMessage(mailSession);

	            // Definiï¿½ï¿½o de quem estï¿½ enviando o email.
	            message.setFrom(remetente);

	            
	             * define o(s) destinatï¿½rio(s) e qual o tipo do destinatï¿½rio. os
	             * possï¿½veis tipos de destinatï¿½rio: TO, CC, BCC.
	             
	            message.setRecipients(Message.RecipientType.TO, arrayEmailDest);

	            message.setRecipients(Message.RecipientType.CC, arrayEmailDestCC);

	            message.setSentDate(new Date());

	            // definiï¿½ï¿½o do assunto do email.
	            message.setSubject(assunto);

	            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
	            message.setHeader("Content-Transfer-Encoding", "quoted-printable");

	            final Multipart corpo = new MimeMultipart();

	            final MimeBodyPart msg = new MimeBodyPart();

	            msg.setContent(mensagem, "text/plain; charset=UTF-8");

	            if ( anexo != null && anexo.length != 0 )
	            {
	                final MimeBodyPart parAnexo = new MimeBodyPart();

	                parAnexo.setDataHandler(
	                        new DataHandler(new ByteArrayDataSource(anexo, "aplication/pdf")));

	                parAnexo.setFileName(nomeArquivoAnexo + ".pdf");

	                corpo.addBodyPart(parAnexo);
	            }
	            corpo.addBodyPart(msg);
	            message.setContent(corpo, "text/plain; charset=UTF-8");

	            // a linha de cï¿½digo seguinte ï¿½ a responsï¿½vel pelo envio do email.
	            Transport.send(message);
	        }
	        catch (final Exception e)
	        {
	            FacesUtils.log(Email.class,
	                    "Erro ao enviar e-mail, detalhes: " + e.getMessage());
	        }
	    }

	    *//**
	     * Envia email para mais de um destinatï¿½rio principal e com cï¿½pia.
	     *
	     * @author Brunno Oliveira[brunnogoncalves@gmail.com] 11/06/2012 - 09:24:48.
	     * @param mailServer
	     * @param assunto
	     * @param emailRementente
	     * @param emailDestinatarios
	     * @param emailDestinatariosCC
	     * @param mensagem
	     *//*
	    public static void enviarEmail(final String mailServer, final String assunto,
	            final String emailRementente, final List<String> emailDestinatarios,
	            final List<String> emailDestinatariosCC, final String mensagem)
	    {
	        try
	        {
	            final Properties mailProps = new Properties();

	            // definiï¿½ï¿½o do mailserver
	            mailProps.put("mail.smtp.host", getIpServidorEmail());

	            final Session mailSession = Session.getDefaultInstance(mailProps,
	                    authenticator());

	            
	             * Alimenta as listas de destinatï¿½rios
	             
	            final List<InternetAddress> emailsDest = new ArrayList<>();
	            final List<InternetAddress> emailsDestCC = new ArrayList<>();
	            InternetAddress email = null;

	            if ( !TypeUtils.isBlankOrNullOrEmpty(emailDestinatarios) )
	            {
	                for (final String e : emailDestinatarios)
	                {
	                    email = new InternetAddress(e);
	                    emailsDest.add(email);
	                }

	            }

	            if ( !TypeUtils.isBlankOrNullOrEmpty(emailDestinatariosCC) )
	            {
	                for (final String e : emailDestinatariosCC)
	                {
	                    email = new InternetAddress(e);
	                    emailsDestCC.add(email);
	                }
	            }

	            final InternetAddress[] arrayEmailDest = emailsDest
	                    .toArray(new InternetAddress[emailsDest.size()]);
	            final InternetAddress[] arrayEmailDestCC = emailsDestCC
	                    .toArray(new InternetAddress[emailsDestCC.size()]);

	            
	             * As duas linhas seguintes de cï¿½digo, colocam no formato de
	             * endereï¿½os, supostamente vï¿½lidos, de email os dados passados pelos
	             * parï¿½metros to e from.
	             
	            final InternetAddress remetente = new InternetAddress(emailRementente);
	            
	             * As duas linhas de cï¿½digo a seguir, sï¿½o responsï¿½veis por setar os
	             * atributos e propriedas necessï¿½rias do objeto message para que o email
	             * seja enviado. inicializaï¿½ï¿½o do objeto Message.
	             
	            final Message message = new MimeMessage(mailSession);

	            // Definiï¿½ï¿½o de quem estï¿½ enviando o email.
	            message.setFrom(remetente);

	            
	             * define o(s) destinatï¿½rio(s) e qual o tipo do destinatï¿½rio. os
	             * possï¿½veis tipos de destinatï¿½rio: TO, CC, BCC.
	             
	            message.setRecipients(Message.RecipientType.TO, arrayEmailDest);

	            message.setRecipients(Message.RecipientType.CC, arrayEmailDestCC);

	            message.setSentDate(new Date());

	            // definiï¿½ï¿½o do assunto do email.
	            message.setSubject(assunto);

	            message.setContent(mensagem, "text/plain; charset=UTF-8");

	            // a linha de cï¿½digo seguinte ï¿½ a responsï¿½vel pelo envio do email.
	            Transport.send(message);
	        }
	        catch (final Exception e)
	        {
	            FacesUtils.log(Email.class,
	                    "Erro ao enviar e-mail, detalhes: " + e.getMessage());
	        }
	    }

	    public static void enviarEmail(final String mailServer, final String assunto,
	            final String emailRementente, final List<String> emailDestinatarios,
	            final List<String> emailDestinatariosCC, final String mensagem,
	            final Map<String, byte[]> anexos, final String nomeArquivoAnexo)
	    {
	        try
	        {
	            final Properties mailProps = new Properties();

	            // definiï¿½ï¿½o do mailserver
	            mailProps.put("mail.smtp.host", getIpServidorEmail());

	            final Session mailSession = Session.getDefaultInstance(mailProps,
	                    authenticator());

	            
	             * Alimenta as listas de destinatï¿½rios
	             
	            final List<InternetAddress> emailsDest = new ArrayList<>();
	            final List<InternetAddress> emailsDestCC = new ArrayList<>();
	            InternetAddress email = null;

	            if ( !TypeUtils.isBlankOrNullOrEmpty(emailDestinatarios) )
	            {
	                for (final String e : emailDestinatarios)
	                {
	                    email = new InternetAddress(e);
	                    emailsDest.add(email);
	                }

	            }

	            if ( !TypeUtils.isBlankOrNullOrEmpty(emailDestinatariosCC) )
	            {
	                for (final String e : emailDestinatariosCC)
	                {
	                    email = new InternetAddress(e);
	                    emailsDestCC.add(email);
	                }
	            }

	            final InternetAddress[] arrayEmailDest = emailsDest
	                    .toArray(new InternetAddress[emailsDest.size()]);
	            final InternetAddress[] arrayEmailDestCC = emailsDestCC
	                    .toArray(new InternetAddress[emailsDestCC.size()]);

	            
	             * As duas linhas seguintes de cï¿½digo, colocam no formato de
	             * endereï¿½os, supostamente vï¿½lidos, de email os dados passados pelos
	             * parï¿½metros to e from.
	             
	            final InternetAddress remetente = new InternetAddress(emailRementente);
	            
	             * As duas linhas de cï¿½digo a seguir, sï¿½o responsï¿½veis por setar os
	             * atributos e propriedas necessï¿½rias do objeto message para que o email
	             * seja enviado. inicializaï¿½ï¿½o do objeto Message.
	             
	            final Message message = new MimeMessage(mailSession);

	            // Definiï¿½ï¿½o de quem estï¿½ enviando o email.
	            message.setFrom(remetente);

	            
	             * define o(s) destinatï¿½rio(s) e qual o tipo do destinatï¿½rio. os
	             * possï¿½veis tipos de destinatï¿½rio: TO, CC, BCC.
	             
	            message.setRecipients(Message.RecipientType.TO, arrayEmailDest);

	            message.setRecipients(Message.RecipientType.CC, arrayEmailDestCC);

	            message.setSentDate(new Date());

	            // definiï¿½ï¿½o do assunto do email.
	            message.setSubject(assunto);

	            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
	            message.setHeader("Content-Transfer-Encoding", "quoted-printable");

	            final Multipart corpo = new MimeMultipart();

	            final MimeBodyPart msg = new MimeBodyPart();

	            msg.setContent(mensagem, "text/plain; charset=UTF-8");

	            final Set<String> keysAnexos = anexos.keySet();
	            for (final String key : keysAnexos)
	            {
	                final byte[] anexo = anexos.get(key);

	                final MimeBodyPart parAnexo = new MimeBodyPart();

	                parAnexo.setDataHandler(
	                        new DataHandler(new ByteArrayDataSource(anexo, "aplication/pdf")));

	                final String nomArqAnexo = nomeArquivoAnexo != null
	                        ? "_" + nomeArquivoAnexo
	                        : "";
	                parAnexo.setFileName(key + nomArqAnexo + ".pdf");

	                corpo.addBodyPart(parAnexo);
	            }
	            corpo.addBodyPart(msg);
	            message.setContent(corpo, "text/plain; charset=UTF-8");

	            // a linha de cï¿½digo seguinte ï¿½ a responsï¿½vel pelo envio do email.
	            Transport.send(message);
	        }
	        catch (final Exception e)
	        {
	            FacesUtils.log(Email.class,
	                    "Erro ao enviar e-mail, detalhes: " + e.getMessage());
	        }
	    }

	    *//**
	     * Enviar email.
	     *
	     * @author Aldivone Correia[aldivone@gmail.com] - 24/04/2009 - 13:28:25
	     * @param assunto
	     *          Assunto do Email.
	     * @param emailRementente
	     *          Email do rementente.
	     * @param emailDestinatario
	     *          Email do destinatï¿½rio.
	     * @param mensagem
	     *          Corpo do Email.
	     *//*
	    public static void enviarEmail(final String assunto,
	            final String emailRementente, final String emailDestinatario,
	            final String mensagem)
	    {
	        enviarEmail(getIpServidorEmail(), assunto, emailRementente,
	                emailDestinatario, mensagem);
	    }

	    *//**
	     * Enviar email.
	     *
	     * @author Aldivone Correia[aldivone@gmail.com] - 24/04/2009 - 13:28:21
	     * @param emailRementente
	     *          Email do rementente.
	     * @param emailDestinatario
	     *          Email do destinatï¿½rio.
	     * @param mensagem
	     *          Corpo do Email.
	     *//*
	    public static void enviarEmail(final String assunto,
	            final String emailDestinatario, final String mensagem)
	    {
	        enviarEmail(getIpServidorEmail(), assunto, Constants.EMAIL_PRAX,
	                emailDestinatario, mensagem);
	    }

	    *//**
	     * Enviar e-mail prioridade
	     *
	     * @see a prioridade 1 é alta, 3 normal e 5 baixa.
	     * @author Daniel Oliveira[daniel.andrade@cagece.com] 30/06/2016 - 16:35:11.
	     * @param assunto
	     * @param emailDestinatario
	     * @param mensagem
	     *//*
	    public static void enviarEmailPrioridade(final String assunto,
	            final String emailDestinatario, final String mensagem,
	            final Integer prioridade)
	    {
	        enviarEmailPrioridade(getIpServidorEmail(), assunto, Constants.EMAIL_PRAX,
	                emailDestinatario, mensagem, prioridade);
	    }

	    *//**
	     * Escrever o conteï¿½do de um exceï¿½ï¿½o numa string.
	     *
	     * @author Aldivone Correia[aldivone@gmail.com] - 23/07/2009 - 10:01:21
	     * @param exc
	     *          Exceï¿½ï¿½o a ser impressa.
	     * @return O conteï¿½do da exceï¿½ï¿½o em uma string.
	     *//*
	    public static String imprimirExcecaoEmString(final Exception exc)
	    {
	        try
	        {
	            if ( exc != null )
	            {
	                final StringBuilder text = new StringBuilder();
	                final StringWriter sw = new StringWriter();
	                final PrintWriter pw = new PrintWriter(sw, true);
	                exc.printStackTrace(pw);
	                text.append(sw.getBuffer().toString() + " \n");
	                final SimpleDateFormat sdf = new SimpleDateFormat(
	                        "dd/MM/yyyy hh:mm:ss");
	                text.append("\r\n ==> Data do log: " + sdf.format(new Date()) + "\r\n");
	                return "#### \r\n" + text.toString() + " #### \r\n";
	            }
	            return "Exceção lançada está nula!";
	        }
	        catch (final Exception e2)
	        {
	            return "Erro ao escrever a exceção na string!";
	        }
	    }

	    *//**
	     * Enviar email com mensagem de erro.
	     *
	     * @param assunto
	     *          Assunto do Email.
	     * @param emailDestinatario
	     *          Email da pessoa a ser enviado o email.
	     * @param exception
	     *          Exceï¿½ï¿½o a ser enviada.
	     *//*
	    public static void enviarEmail(final String assunto, String emailDestinatario,
	            final Exception exception)
	    {
	        if ( TypeUtils.isBlankOrNull(emailDestinatario) )
	        {
	            emailDestinatario = Constants.EMAIL_ERROR_DESTINATARIO;
	        }
	        final boolean permitiEnviarEmailException = verificarSePodeEnviarEmailExcecao(
	                exception);
	        if ( permitiEnviarEmailException )
	        {
	            enviarEmail(getIpServidorEmail(), assunto, Constants.EMAIL_PRAX,
	                    emailDestinatario, imprimirExcecaoEmString(exception));
	        }
	    }

	    private static boolean verificarSePodeEnviarEmailExcecao(
	            final Exception exception)
	    {
	        final boolean excecaoNegocio = exception instanceof NgcExcecaoGenericaRegraDeNegocioException
	                || exception instanceof RegraNegocioException;
	        final boolean permitiEnviarEmailException = FacesUtils.isRodandoNoServidor()
	                && !excecaoNegocio;
	        return permitiEnviarEmailException;
	    }

	    *//**
	     * Enviar email com log de erro e detalhes do problema.
	     *
	     * 07/07/2015 - 11:21:15.
	     *
	     * @param klass
	     * @param dadosAdicionais
	     * @param exception
	     *//*
	    public static void enviarEmailErro(final Class<?> klass,
	            final Exception exception)
	    {
	        enviarEmailErro(klass, null, exception);
	    }

	    *//**
	     * Enviar email com log de erro e detalhes do problema.
	     *
	     * 07/07/2015 - 11:21:15.
	     *
	     * @param klass
	     * @param dadosAdicionais
	     * @param exception
	     *//*
	    public static void enviarEmailErro(final Class<?> klass,
	            final String dadosAdicionais, final Exception exception)
	    {
	        final boolean permitiEnviarEmailException = verificarSePodeEnviarEmailExcecao(
	                exception);
	        if ( permitiEnviarEmailException )
	        {
	            final String assunto = getAssuntoEmailErro(klass);
	            final StringBuilder mensagem = new StringBuilder();
	            if ( FacesUtils.getUsuarioLogado() != null
	                    && FacesUtils.getUsuarioLogado().getBtpColaborador() != null )
	            {
	                mensagem.append("<strong>Usuário do sistema:</strong> "
	                        + FacesUtils.getUsuarioLogado().getUsuLgnUsuario() + " - "
	                        + FacesUtils.getUsuarioLogado().getDescricao() + " <br>");
	            }
	            else
	            {
	                mensagem.append("<strong>Usuário do sistema:</strong>"
	                        + " Executado pelo sistema.<br>");
	            }
	            mensagem.append("<strong>Ip do usuário:</strong> "
	                    + FacesUtils.getRemotoIp() + " <br>");
	            if ( FacesUtils.getUsuarioLogado() != null && FacesUtils
	                    .getUsuarioLogado().getBtpUnidadeAdministrativa() != null )
	            {
	                mensagem
	                        .append(
	                                "<strong>Unidade:</strong> "
	                                        + FacesUtils.getUsuarioLogado()
	                                                .getBtpUnidadeAdministrativa().getUadSglUnidadeAdmin()
	                                        + " <br>");
	            }
	            if ( dadosAdicionais != null )
	            {
	                mensagem.append("<strong>Informações adicionais:</strong> <br>");
	                mensagem.append(dadosAdicionais + " <br>");
	            }
	            mensagem.append("<strong>Log do erro:</strong> <br>");
	            mensagem.append(imprimirExcecaoEmString(exception));
	            enviarEmail(getIpServidorEmail(), assunto, Constants.EMAIL_PRAX,
	                    Constants.EMAIL_ERROR_DESTINATARIO, mensagem.toString());
	        }
	    }

	    *//**
	     *
	     * @author Dirceu da Silva [dirceu.rodrigues@cagece.com.br -
	     *         dirceusr@gmail.com] 24/01/2017 - 09:09:14.
	     * @param klass
	     * @return
	     *//*
	    public static String getAssuntoEmailErro(final Class<?> klass)
	    {
	        final SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmssSSS");

	        final String assunto = "ERRO " + sdf.format(new Date()) + " ==> Servidor: "
	                + FacesUtils.getIpServidorReal() + ", durante execução da classe: "
	                + klass.getSimpleName();
	        return assunto;
	    }

	    *//**
	     * Enviar email com mensagem de erro.
	     *
	     * @param assunto
	     *          Assunto do Email.
	     * @param corporEmail
	     *          Exceï¿½ï¿½o a ser enviada.
	     *//*
	    public static void enviarEmail(final String assunto, final String corpoEmail)
	    {
	        enviarEmail(getIpServidorEmail(), assunto, Constants.EMAIL_PRAX, null,
	                corpoEmail);
	    }

	    *//**
	     * Buscar o endereï¿½o do servidor de email.
	     *
	     * @return endereï¿½o do servidor de email.
	     *//*
	    public static String getIpServidorEmail()
	    {
	        try
	        {
	            return ModeloUtils.getConfiguracaoSistema(CHAVE_IP_SERVIDOR_EMAIL);
	        }
	        catch (final Exception ex)
	        {
	            return null;
	        }
	    }*/
}
