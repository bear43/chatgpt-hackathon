package ru.tinkoff.neurosurge.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages/admin")
public class AdminController {
    @GetMapping
    public String get(Model model) {
        record File(int id, String name, Collection<String> tags) {}
        List<File> files = List.of(
                new File(1, "loyalty-programs-bravo-rules.pdf", List.of(
                        "Программа лояльности \"Браво\"",
                        "держатели кредитных карт Тинькофф Платинум",
                        "Условия комплексного банковского обслуживания физических лиц",
                        "договор участия",
                        "Тарифные планы",
                        "заявка на карту",
                        "безоговорочное принятие условий",
                        "повышение лояльности клиентов",
                        "операции с использованием карты",
                        "начисление бонусов (Баллов)",
                        "компенсация стоимости банковских услуг и покупок",
                        "MCC-коды",
                        "транзакционные Баллы",
                        "призовые и иные Баллы",
                        "корректировочные Баллы",
                        "начисление и списание Баллов")),
                new File(2, "loyalty-program-platinum.pdf", List.of(
                        "Программа лояльности",
                        "Tinkoff Platinum",
                        "Tinkoff Platinum Premium",
                        "Tinkoff Platinum Private",
                        "Кредитные карты",
                        "Условия сервиса Tinkoff Premium",
                        "Условия сервиса Tinkoff Private",
                        "Условия комплексного банковского обслуживания физических лиц",
                        "Договор участия в программе",
                        "Заявка на карту с программой",
                        "Тарифные планы",
                        "Повышение лояльности",
                        "Мотивация клиентов",
                        "Операции с использованием карты",
                        "Кэшбэк",
                        "Бонусы",
                        "Категории операций",
                        "MCC (группы товаров)",
                        "Merchant Name (уникальные сети магазинов)",
                        "Операции в Мобильном Банке и/или Интернет-Банке",
                        "Выбор категорий",
                        "Описание категорий",
                        "Tinkoff Black",
                        "Изменение списка категорий"
                )),
                new File(3, "loyalty-program-target_rules.pdf", List.of(
                        "Банкская программа лояльности \"Тинькофф Таргет\"",
                        "Банкская программа лояльности \"Тинькофф Путешествия\"",
                        "Условия настоящей программы",
                        "Мошеннические действия",
                        "Злоупотребление привилегиями и поощрениями",
                        "Участник программы",
                        "Категории MCC (Merchant Category Code)",
                        "Описание категорий",
                        "Картсчет Клиента",
                        "Расчетный период",
                        "Максимальная сумма бонусов",
                        "Исключение из программы",
                        "Уведомление о доступной сумме бонусов",
                        "Изменение и дополнение условий программы",
                        "Подтверждение согласия с новой редакцией программы",
                        "Сокращение количества начисляемых бонусов",
                        "Нарушение условий программы",
                        "Партнеры",
                        "Описание категорий MCC",
                        "Описание категорий по приложению к программе лояльности Tinkoff Platinum",
                        "Описание категорий по MCC для различных товаров и услуг"
                )));
        model.addAttribute("files", files);
        return "admin";
    }
}
